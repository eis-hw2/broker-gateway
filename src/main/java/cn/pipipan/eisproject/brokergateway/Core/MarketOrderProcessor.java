package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderBlotterRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Service.OrderBlotterService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component("MarketOrderProcessor")
public class MarketOrderProcessor extends OrderProcessor{
    @Autowired
    OrderBlotterService orderBlotterService;

    @Override
    public Order process(Order order, List<TraderComposite> buyer, List<TraderComposite> seller, List<Order> marketOrders) {
        List<TraderComposite> traderComposites = order.getPosition() == Order.BUYER ? seller : buyer;
        tradeWithLimitOrders(order, traderComposites);
        if (order.getCount() > 0) marketOrders.add(order);
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
                    orderBlotterService.sealOneDeal(order, tradedOrder);
                    orderIterator.remove();
                }
                else {
                    orderBlotterService.sealOneDeal(tradedOrder, order);
                    return;
                }
            }
            it.remove();
        }
    }
}
