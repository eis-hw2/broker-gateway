package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;

import java.util.ArrayList;
import java.util.List;

public class TraderComposite {
    public static TraderComposite createTraderComposite(Order order){
        TraderComposite res = new TraderComposite();
        res.setPrice(order.getPrice());
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        res.setOrders(orders);
        return res;
    }

    int price;
    List<Order> orders;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(Order order){
        orders.add(order);
    }
}
