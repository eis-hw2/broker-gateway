package cn.pipipan.eisproject.brokergateway.Dao.DaoImpl;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import cn.pipipan.eisproject.brokergateway.Dao.TraderCompositeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TraderCompositeRepositoryImpl implements TraderCompositeRepository {
    private Map<String, List<TraderComposite>> buyer = new HashMap<>();
    private Map<String, List<TraderComposite>> seller = new HashMap<>();

    @Override
    public List<TraderComposite> getBuyerTraderCompositeByItemId(String itemId) {
        initSpecificMapValue(itemId, buyer);
        return buyer.get(itemId);
    }

    @Override
    public List<TraderComposite> getSellerTraderCompositeByItemId(String itemId) {
        initSpecificMapValue(itemId, seller);
        return seller.get(itemId);
    }

    private void initSpecificMapValue(String itemId, Map<String, List<TraderComposite>> map) {
        map.computeIfAbsent(itemId, k -> new ArrayList<>());
    }
}
