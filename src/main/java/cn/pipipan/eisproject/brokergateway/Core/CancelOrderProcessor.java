package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component("CancelOrderProcessor")
public class CancelOrderProcessor extends OrderProcessor{
    @Autowired
    OrderRepository orderRepository;

    //TODO 加入错误处理
    @Override
    public Order process(Order order) {
        Order canceledOrder = orderRepository.findOrderById(order.getOrderId());
        if (canceledOrder == null) return order;
        init(canceledOrder);
        completeOrder(order, canceledOrder);

        // cancel the stopOrder
        Iterator<Order> stopOrderIt = stopOrders.iterator();
        while (stopOrderIt.hasNext()){
            Order stopOrder = stopOrderIt.next();
            if (stopOrder.getId().equals(canceledOrder.getId())) {
                stopOrderIt.remove();
                order.setCount(stopOrder.getCount());
                log.info("stopOrders size after cancel stopOrder: {}", stopOrders.size());
                coreDataStructureRepository.saveRemainedStopOrdersByItemId(canceledOrder.getItemId(), marketOrders);
                return order;
            }
        }

        // cancel the marketOrder
        Iterator<Order> marketOrderIt = marketOrders.iterator();
        while (marketOrderIt.hasNext()){
            Order marketOrder = marketOrderIt.next();
            if (marketOrder.getId().equals(canceledOrder.getId())) {
                marketOrderIt.remove();
                order.setCount(marketOrder.getCount());
                log.info("marketOrders size after cancel marketOrder: {}", marketOrders.size());
                coreDataStructureRepository.saveRemainedMarketOrdersByItemId(canceledOrder.getItemId(), marketOrders);
                return order;
            }
        }

        // cancel the LimitOrder
        List<TraderComposite> traderComposites = order.getPosition() == Order.BUYER ? buyer : seller;
        Iterator<TraderComposite> traderCompositeIt = getIterator(order, traderComposites);
        if (traderCompositeIt != null) {
            TraderComposite traderComposite = traderCompositeIt.next();
            Iterator<Order> it = traderComposite.getOrders().iterator();
            while (it.hasNext()){
                Order foundOrder = it.next();
                if (foundOrder.getId().equals(canceledOrder.getId())) {
                    order.setCount(foundOrder.getCount());
                    it.remove();
                    log.info("LimitOrders size after cancel limitOrder: {}", traderComposites.size());
                    break;
                }
            }
            if (traderComposite.getOrders().isEmpty()) {
                log.info("remove empty traderComposite");
                traderCompositeIt.remove();
            }
            if (canceledOrder.getPosition() == Order.BUYER) coreDataStructureRepository.saveBuyerTraderCompositeByItemId(canceledOrder.getItemId(), traderComposites);
            else coreDataStructureRepository.saveSellerTraderCompositeByItemId(canceledOrder.getItemId(), traderComposites);
        }
        return order;
    }

    private Iterator<TraderComposite> getIterator(Order order, List<TraderComposite> traderCompositeList) {
        //TODO 使用二分搜索查找
        for (int i=0; i<traderCompositeList.size(); ++i){
            if (traderCompositeList.get(i).getPrice() == order.getPrice()) return traderCompositeList.listIterator(i);
        }
        return null;
    }

    private void completeOrder(Order order, Order canceledOrder) {
        order.setPrice(canceledOrder.getPrice());
        order.setPosition(canceledOrder.getPosition());
        order.setItemId(canceledOrder.getItemId());
    }
}
