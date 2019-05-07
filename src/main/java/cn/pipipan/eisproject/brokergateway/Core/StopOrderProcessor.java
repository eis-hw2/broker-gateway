package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.stereotype.Component;

//TODO 通过RabbitMQ监听变化
@Component("StopOrderProcessor")
public class StopOrderProcessor extends OrderProcessor{
    @Override
    public void doProcess(Order order) {
    }
}
