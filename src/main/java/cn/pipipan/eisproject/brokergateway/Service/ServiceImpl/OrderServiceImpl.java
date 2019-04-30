package cn.pipipan.eisproject.brokergateway.Service.ServiceImpl;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Core.OrderProcessor;
import cn.pipipan.eisproject.brokergateway.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    
    Map<String, List<TraderComposite>> buyer = new HashMap<>();
    Map<String, List<TraderComposite>> seller = new HashMap<>();
    
    OrderProcessor orderProcessor;

    public void process(Order order){
        orderRepository.save(order);
        initSpecificMapValue(order, buyer);
        initSpecificMapValue(order, seller);
        orderProcessor.process(order,buyer.get(order.getItemId()), seller.get(order.getItemId()));
    }

    private void initSpecificMapValue(Order order, Map<String, List<TraderComposite>> map) {
        if (map.get(order.getItemId()) == null) {
            map.put(order.getItemId(), new ArrayList<>());
        }
    }

    public void setOrderProcessor(OrderProcessor orderProcessor){
        this.orderProcessor = orderProcessor;
    }
}
