package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

//TODO 通过RabbitMQ监听变化
@Component("StopOrderProcessor")
public class StopOrderProcessor extends OrderProcessor{
    @Override
    public Order process(Order order) {
        super.process(order);
        return order;
    }
}
