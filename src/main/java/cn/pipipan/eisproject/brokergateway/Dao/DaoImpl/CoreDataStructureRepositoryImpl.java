package cn.pipipan.eisproject.brokergateway.Dao.DaoImpl;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import cn.pipipan.eisproject.brokergateway.Dao.CoreDataStructureRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoreDataStructureRepositoryImpl implements CoreDataStructureRepository {
    private Map<String, List<TraderComposite>> buyer = new HashMap<>();
    private Map<String, List<TraderComposite>> seller = new HashMap<>();
    private Map<String, List<Order>> remainedMarketOrders = new HashMap<>();
    private Map<String, List<Order>> remainedStopOrders = new HashMap<>();

    @Override
    public List<TraderComposite> getBuyerTraderCompositeByItemId(String itemId) {
        buyer.computeIfAbsent(itemId, k -> new ArrayList<>());
        return buyer.get(itemId);
    }

    @Override
    public List<Order> getRemainedMarketOrdersByItemId(String itemId) {
        remainedMarketOrders.computeIfAbsent(itemId, k -> new ArrayList<>());
        return remainedMarketOrders.get(itemId);
    }

    @Override
    public List<Order> getRemainedStopOrdersByItemId(String itemId) {
        remainedStopOrders.computeIfAbsent(itemId, k -> new ArrayList<>());
        return remainedStopOrders.get(itemId);
    }

    @Override
    public List<TraderComposite> saveBuyerTraderCompositeByItemId(String itemId, List<TraderComposite> traderComposites) {
        return traderComposites;
    }

    @Override
    public List<TraderComposite> saveSellerTraderCompositeByItemId(String itemId, List<TraderComposite> traderComposites) {
        return traderComposites;
    }

    @Override
    public List<Order> saveRemainedMarketOrdersByItemId(String itemId, List<Order> orders) {
        return orders;
    }

    @Override
    public List<Order> saveRemainedStopOrdersByItemId(String itemId, List<Order> orders) {
        return orders;
    }

    @Override
    public void clear() {
        this.buyer.clear();
        this.seller.clear();
        this.remainedMarketOrders.clear();
        this.remainedStopOrders.clear();
    }

    @Override
    public List<TraderComposite> getSellerTraderCompositeByItemId(String itemId) {
        seller.computeIfAbsent(itemId, k -> new ArrayList<>());
        return seller.get(itemId);
    }
}
