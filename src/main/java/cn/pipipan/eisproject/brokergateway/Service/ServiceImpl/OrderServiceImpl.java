package cn.pipipan.eisproject.brokergateway.Service.ServiceImpl;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Core.OrderProcessor;
import cn.pipipan.eisproject.brokergateway.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    private OrderProcessor orderProcessor;

    public Order process(Order order){
        orderRepository.save(order);
        return orderProcessor.process(order);
    }

    public void setOrderProcessor(OrderProcessor orderProcessor){
        this.orderProcessor = orderProcessor;
    }
}
