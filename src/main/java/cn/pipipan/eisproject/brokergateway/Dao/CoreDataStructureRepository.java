package cn.pipipan.eisproject.brokergateway.Dao;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface CoreDataStructureRepository {
    public List<TraderComposite> getBuyerTraderCompositeByItemId(String itemId);
    public List<TraderComposite> getSellerTraderCompositeByItemId(String itemId);
    public List<Order> getRemainedMarketOrdersByItemId(String itemId);
    public List<Order> getRemainedStopOrdersByItemId(String itemId);
    public List<TraderComposite> saveBuyerTraderCompositeByItemId(String itemId, List<TraderComposite> traderComposites);
    public List<TraderComposite> saveSellerTraderCompositeByItemId(String itemId, List<TraderComposite> traderComposites);
    public List<Order> saveRemainedMarketOrdersByItemId(String itemId, List<Order> orders);
    public List<Order> saveRemainedStopOrdersByItemId(String itemId, List<Order> orders);
}
