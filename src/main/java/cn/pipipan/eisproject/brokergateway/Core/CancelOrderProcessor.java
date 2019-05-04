package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("CancelOrderProcessor")
public class CancelOrderProcessor extends OrderProcessor{
    @Override
    public Order process(Order order) {
        super.process(order);
        return order;
    }
}
