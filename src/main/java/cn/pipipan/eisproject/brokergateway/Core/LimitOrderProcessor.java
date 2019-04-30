package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LimitOrderProcessor")
public class LimitOrderProcessor extends OrderProcessor{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void process(Order order, List<TraderComposite> buyer, List<TraderComposite> seller) {
        System.out.println(order.getId());
    }
}
