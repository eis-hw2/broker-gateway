package cn.pipipan.eisproject.brokergateway.Service.ServiceImpl;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Dao.TraderCompositeRepository;
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
    @Autowired
    TraderCompositeRepository traderCompositeRepository;

    private OrderProcessor orderProcessor;

    public Order process(Order order){
        orderRepository.save(order);
        List<TraderComposite> buyerTraderComposite = traderCompositeRepository.getBuyerTraderCompositeByItemId(order.getItemId());
        List<TraderComposite> sellerTraderComposite = traderCompositeRepository.getSellerTraderCompositeByItemId(order.getItemId());
        orderProcessor.process(order, buyerTraderComposite, sellerTraderComposite);
        return order;
    }

    public void setOrderProcessor(OrderProcessor orderProcessor){
        this.orderProcessor = orderProcessor;
    }
}
