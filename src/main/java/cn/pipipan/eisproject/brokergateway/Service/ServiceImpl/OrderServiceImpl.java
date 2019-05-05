package cn.pipipan.eisproject.brokergateway.Service.ServiceImpl;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Core.OrderProcessor;
import cn.pipipan.eisproject.brokergateway.EventHandler.OrderEventProducerWithTranslator;
import cn.pipipan.eisproject.brokergateway.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderEventProducerWithTranslator orderEventProducerWithTranslator;

    public Order process(Order order){
        orderRepository.save(order);
        orderEventProducerWithTranslator.onData(order);
        return order;
    }
}
