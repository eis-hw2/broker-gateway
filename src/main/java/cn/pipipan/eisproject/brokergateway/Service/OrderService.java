package cn.pipipan.eisproject.brokergateway.Service;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Core.OrderProcessor;

public interface OrderService {
    public void process(Order order);
    public void setOrderProcessor(OrderProcessor orderProcessor);
}