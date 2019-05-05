package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Domain.OrderBlotter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderBlotterRepository extends MongoRepository<OrderBlotter, String> {
}
