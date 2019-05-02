package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("MarketOrderProcessor")
public class MarketOrderProcessor extends OrderProcessor{
    @Override
    public Order process(Order order, List<TraderComposite> buyer, List<TraderComposite> seller, List<Order> MarketOrders) {
        return order;
    }
}
