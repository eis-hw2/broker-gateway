package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Service.OrderBlotterService;
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
    public void doProcess(Order order) {
        List<TraderComposite> traderComposites = order.getPosition() == Order.BUYER ? seller : buyer;
        tradeWithTraderComposite(order, traderComposites);
        if (!order.finished()) addMarketOrderIntoList(order);
    }

    private void addMarketOrderIntoList(Order order) {
        marketOrders.add(order);
        log.info("addMarketOrderIntoList: marketOrderSize: {}", marketOrders.size());
        coreDataStructureRepository.saveRemainedMarketOrdersByItemId(order.getFutureId(), marketOrders);
    }

    private void tradeWithTraderComposite(Order order, List<TraderComposite> traderComposites){
        Iterator<TraderComposite> it = traderComposites.iterator();
        while (it.hasNext() && !order.finished()) {
            TraderComposite traderComposite = it.next();
            tradeWithOrder(order, traderComposite.getOrders(), orderBlotterService);
            if (traderComposite.getOrders().isEmpty()) it.remove();
        }
        if (order.getPosition() == Order.BUYER)  coreDataStructureRepository.saveSellerTraderCompositeByItemId(order.getFutureId(), traderComposites) ;
        else coreDataStructureRepository.saveBuyerTraderCompositeByItemId(order.getFutureId(), traderComposites);
    }
}
