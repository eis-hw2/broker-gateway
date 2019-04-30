package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Domain.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}
