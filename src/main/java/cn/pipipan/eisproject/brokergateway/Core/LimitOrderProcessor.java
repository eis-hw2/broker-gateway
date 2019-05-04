package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;

@Component("LimitOrderProcessor")
public class LimitOrderProcessor extends OrderProcessor{
    @Autowired
    OrderRepository orderRepository;

    //ExecutorService pool;

    @Override
    public Order process(Order order) {
        super.process(order);
        List<TraderComposite> traderComposites = order.getPosition() == Order.BUYER ? seller : buyer;
        List<TraderComposite> waitingComposites = order.getPosition() == Order.SELLER ? seller : buyer;
        tradeWithMarketOrder(order, marketOrders);
        if (order.getCount() <= 0) return order;
        if (traderComposites.isEmpty() || !order.canAfford(traderComposites.get(0).getPrice())){
            insertIntoWaitingQueue(order, waitingComposites);
        }
        else {
            tradeWithLimitOrder(order, marketOrders);
            if (order.getCount() > 0) insertIntoWaitingQueue(order, waitingComposites, 0);
        }
        return order;
    }

    private void tradeWithMarketOrder(Order order, List<Order> marketOrders) {

    }

    private void tradeWithLimitOrder(Order order, List<Order> marketOrders) {

    }

    private void insertIntoWaitingQueue(Order order, List<TraderComposite> waitingComposites, int i) {

    }

    private void insertIntoWaitingQueue(Order order, List<TraderComposite> waitingComposites) {
        int index = binaryFindIndex(order, waitingComposites);
        insertIntoWaitingQueue(order, waitingComposites, index);
    }

    private int binaryFindIndex(Order order, List<TraderComposite> waitingComposites) {
        return 0;
    }
}
