package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

import java.util.List;

public class TraderComposite {
    double price;
    List<Order> orders;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
