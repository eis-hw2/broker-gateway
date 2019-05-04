package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import cn.pipipan.eisproject.brokergateway.Domain.Order;

import java.util.List;

public interface TraderCompositeRepository {
    public List<TraderComposite> getBuyerTraderCompositeByItemId(String itemId);
    public List<TraderComposite> getSellerTraderCompositeByItemId(String itemId);
    public List<Order> getRemainedMarketOrdersByItemId(String itemId);
    public List<Order> getRemainedStopOrdersByItemId(String itemId);
}
