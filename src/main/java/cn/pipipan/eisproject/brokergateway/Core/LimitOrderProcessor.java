package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
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
    public Order process(Order order, List<TraderComposite> buyer, List<TraderComposite> seller, List<Order> marketOrders) {
        synchronized (buyer){
            System.out.println(order.getId());
        }
        return order;
    }
}
