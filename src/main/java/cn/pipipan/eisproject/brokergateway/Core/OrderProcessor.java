package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.CoreDataStructureRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Service.OrderBlotterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

public abstract class OrderProcessor {
    @Autowired
    CoreDataStructureRepository coreDataStructureRepository;

    Logger log = LoggerFactory.getLogger(OrderProcessor.class);
    List<TraderComposite> buyer;
    List<TraderComposite> seller;
    List<Order> marketOrders;
    List<Order> stopOrders;

    public void process(Order order){
        init(order);
        doProcess(order);
    }

    protected abstract void doProcess(Order order);

    protected void init(Order order) {
        buyer = coreDataStructureRepository.getBuyerTraderCompositeByItemId(order.getFutureId());
        seller = coreDataStructureRepository.getSellerTraderCompositeByItemId(order.getFutureId());
        marketOrders = coreDataStructureRepository.getRemainedMarketOrdersByItemId(order.getFutureId());
        stopOrders = coreDataStructureRepository.getRemainedStopOrdersByItemId(order.getFutureId());
        log.info("marketOrders:{}", marketOrders.size());
    }

    protected void tradeWithOrder(Order order, List<Order> orders, OrderBlotterService orderBlotterService) {
        Iterator<Order> orderIterator = orders.iterator();
        while (orderIterator.hasNext()) {
            Order tradedOrder = orderIterator.next();
            // TODO publish orderTransaction Event to AXON
            //orderBlotterService.sealOneDeal(order, tradedOrder, tradedOrder.getUnitPrice());
            int count = Math.min(order.getCount(), tradedOrder.getCount());
            tradedOrder.minusCount(count); order.minusCount(count);
            if (tradedOrder.finished()) orderIterator.remove();
            if (order.finished()) break;
        }
    }
}
