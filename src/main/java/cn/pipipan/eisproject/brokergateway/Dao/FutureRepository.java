package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Domain.Future;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FutureRepository extends MongoRepository<Future, String> {
}
