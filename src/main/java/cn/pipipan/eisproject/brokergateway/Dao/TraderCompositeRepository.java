package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import java.util.List;

public interface TraderCompositeRepository {
    public List<TraderComposite> getBuyerTraderCompositeByItemId(String itemId);
    public List<TraderComposite> getSellerTraderCompositeByItemId(String itemId);
}
