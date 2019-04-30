package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

import java.util.List;

public abstract class OrderProcessor {
    //TODO 可能需要分布式锁？
    public abstract void process(Order order, List<TraderComposite> buyer, List<TraderComposite> seller);
}
