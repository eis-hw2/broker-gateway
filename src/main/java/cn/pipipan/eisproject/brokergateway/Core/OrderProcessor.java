package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.TraderCompositeRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class OrderProcessor {
    @Autowired
    TraderCompositeRepository traderCompositeRepository;

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

    private void init(Order order) {
        buyer = traderCompositeRepository.getBuyerTraderCompositeByItemId(order.getItemId());
        seller = traderCompositeRepository.getSellerTraderCompositeByItemId(order.getItemId());
        marketOrders = traderCompositeRepository.getRemainedMarketOrdersByItemId(order.getItemId());
        stopOrders = traderCompositeRepository.getRemainedStopOrdersByItemId(order.getItemId());
        log.info("marketOrders:{}", marketOrders.size());
    }
}
