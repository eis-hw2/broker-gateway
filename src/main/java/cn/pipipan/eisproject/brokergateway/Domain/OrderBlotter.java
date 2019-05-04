package cn.pipipan.eisproject.brokergateway.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OrderBlotter {
    public static OrderBlotter createOrderBlotter(Order bigOrder, Order smallOrder){
        return new OrderBlotter();
    }

    @Id
    String id;

}
