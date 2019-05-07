package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

public class NullOrderProcessor extends OrderProcessor{
    @Override
    public void doProcess(Order order) {
    }
}
