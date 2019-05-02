package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

import java.util.List;

public class NullOrderProcessor extends OrderProcessor{
    @Override
    public Order process(Order order, List<TraderComposite> buyer, List<TraderComposite> seller) {
        return order;
    }
}
