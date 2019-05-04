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

    //TODO 可能需要分布式锁？
    //TODO 考虑加入线程池
    public Order process(Order order){
        init(order);
        return order;
    }

    protected void init(Order order) {
        buyer = coreDataStructureRepository.getBuyerTraderCompositeByItemId(order.getItemId());
        seller = coreDataStructureRepository.getSellerTraderCompositeByItemId(order.getItemId());
        marketOrders = coreDataStructureRepository.getRemainedMarketOrdersByItemId(order.getItemId());
        stopOrders = coreDataStructureRepository.getRemainedStopOrdersByItemId(order.getItemId());
        log.info("marketOrders:{}", marketOrders.size());
    }

    protected void tradeWithOrder(Order order, List<Order> orders, OrderBlotterService orderBlotterService) {
        Iterator<Order> orderIterator = orders.iterator();
        while (orderIterator.hasNext()) {
            Order tradedOrder = orderIterator.next();
            orderBlotterService.sealOneDeal(order, tradedOrder, tradedOrder.getPrice());
            log.info("tradeWithOrders order.count:{}", order.getCount());
            if (tradedOrder.finished()) orderIterator.remove();
            if (order.finished()) break;
        }
    }
}
