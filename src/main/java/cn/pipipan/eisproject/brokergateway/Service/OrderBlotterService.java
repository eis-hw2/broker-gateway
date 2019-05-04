package cn.pipipan.eisproject.brokergateway.Service;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

public interface OrderBlotterService {
    public void sealOneDeal(Order bigOrder, Order smallOrder);
}
