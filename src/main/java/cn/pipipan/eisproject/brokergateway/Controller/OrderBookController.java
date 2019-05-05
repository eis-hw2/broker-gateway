package cn.pipipan.eisproject.brokergateway.Controller;

import cn.pipipan.eisproject.brokergateway.Dao.CoreDataStructureRepository;
import cn.pipipan.eisproject.brokergateway.Domain.OrderBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/OrderBook")
public class OrderBookController {
    @Autowired
    CoreDataStructureRepository coreDataStructureRepository;
    @GetMapping("/{featureId}")
    public OrderBook getOrderBook(@PathVariable("featureId") String itemId){
        return new OrderBook(coreDataStructureRepository.getBuyerTraderCompositeByItemId(itemId),
                coreDataStructureRepository.getSellerTraderCompositeByItemId(itemId));
    }
}
