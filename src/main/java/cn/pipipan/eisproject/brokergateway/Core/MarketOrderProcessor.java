package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderBlotterRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Service.OrderBlotterService;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component("MarketOrderProcessor")
public class MarketOrderProcessor extends OrderProcessor{
    Logger log = LoggerFactory.getLogger(MarketOrderProcessor.class);
    @Autowired
    OrderBlotterService orderBlotterService;

    @Override
    public Order process(Order order) {
        super.process(order);
        List<TraderComposite> traderComposites = order.getPosition() == Order.BUYER ? seller : buyer;
        tradeWithLimitOrders(order, traderComposites);
        if (order.getCount() > 0) {
            marketOrders.add(order);
            log.info("marketOrderSize: {}", marketOrders.size());
            traderCompositeRepository.saveRemainedMarketOrdersByItemId(order.getItemId(), marketOrders);
        }
        return order;
    }

    private void tradeWithLimitOrders(Order order, List<TraderComposite> traderComposites){
        Iterator<TraderComposite> it = traderComposites.iterator();
        while (it.hasNext()) {
            TraderComposite traderComposite = it.next();
            Iterator<Order> orderIterator = traderComposite.getOrders().iterator();
            while (orderIterator.hasNext()) {
                Order tradedOrder = orderIterator.next();
                if (tradedOrder.getCount() <= order.getCount()){
                    orderBlotterService.sealOneDeal(order, tradedOrder, tradedOrder.getPrice());
                    orderIterator.remove();
                }
                else {
                    orderBlotterService.sealOneDeal(tradedOrder, order, tradedOrder.getPrice());
                    if (order.getPrice() == Order.BUYER)  traderCompositeRepository.saveSellerTraderCompositeByItemId(order.getItemId(), traderComposites) ;
                    else traderCompositeRepository.saveBuyerTraderCompositeByItemId(order.getItemId(), traderComposites);
                    return;
                }
            }
            it.remove();
        }
    }
}
