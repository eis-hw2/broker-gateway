package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
