package cn.pipipan.eisproject.brokergateway.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Item {
    @Id
    String id;
    String name;
    String description;
}
