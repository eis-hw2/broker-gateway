package cn.pipipan.eisproject.brokergateway.EventHandler;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

public class OrderEvent {
    private Order order;

    public void set(Order order){
        this.order = order;
    }

    public Order getOrder(){
        return order;
    }
}
