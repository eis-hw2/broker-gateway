package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Dao.OrderRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Service.OrderBlotterService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Component("LimitOrderProcessor")
public class LimitOrderProcessor extends OrderProcessor{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderBlotterService orderBlotterService;

    //ExecutorService pool;

    @Override
    public Order process(Order order) {
        super.process(order);
        List<TraderComposite> traderComposites = order.getPosition() == Order.BUYER ? seller : buyer;
        List<TraderComposite> waitingComposites = order.getPosition() == Order.SELLER ? seller : buyer;
        tradeWithMarketOrder(order, marketOrders);
        if (order.finished()) return order;
        if (traderComposites.isEmpty()){
            insertIntoWaitingQueue(order, waitingComposites);
        }
        else {
            tradeWithTraderComposite(order, traderComposites);
            if (!order.finished()) insertIntoWaitingQueue(order, waitingComposites);
        }
        log.info("seller: {}", JSONObject.toJSONString(seller));
        log.info("buyer: {}", JSONObject.toJSONString(buyer));
        return order;
    }

    private void tradeWithMarketOrder(Order order, List<Order> marketOrders) {
        tradeWithOrder(order, marketOrders, orderBlotterService);
        coreDataStructureRepository.saveRemainedMarketOrdersByItemId(order.getFutureId(), marketOrders);
    }

    private void tradeWithTraderComposite(Order order, List<TraderComposite> traderComposites) {
        if (order.getPosition() == Order.BUYER){
            Iterator<TraderComposite> it = traderComposites.iterator();
            while (it.hasNext() && !order.finished()) {
                TraderComposite traderComposite = it.next();
                if (!order.canAfford(traderComposite.getPrice())) break;
                tradeWithOrder(order, traderComposite.getOrders(), orderBlotterService);
                if (traderComposite.getOrders().isEmpty()) it.remove();
            }
            coreDataStructureRepository.saveSellerTraderCompositeByItemId(order.getFutureId(), traderComposites) ;
        }
        else {
            log.info("in trader position");
            ListIterator<TraderComposite> it = traderComposites.listIterator(traderComposites.size());
            while (it.hasPrevious() && !order.finished()) {
                TraderComposite traderComposite = it.previous();
                log.info("buyer informaiton: {}", JSONObject.toJSONString(traderComposite));
                if (!order.canAfford(traderComposite.getPrice())) break;
                tradeWithOrder(order, traderComposite.getOrders(), orderBlotterService);
                if (traderComposite.getOrders().isEmpty()) it.remove();
            }
            coreDataStructureRepository.saveBuyerTraderCompositeByItemId(order.getFutureId(), traderComposites);
        }
    }

    private void insertIntoWaitingQueue(Order order, List<TraderComposite> waitingComposites, int i) {
        if (i == waitingComposites.size()) {
            waitingComposites.add(TraderComposite.createTraderComposite(order));
        }
        else if (waitingComposites.get(i).getPrice() == order.getUnitPrice()) {
            waitingComposites.get(i).addOrder(order);
        }
        else {
            waitingComposites.add(i, TraderComposite.createTraderComposite(order));
        }

        if (order.getPosition() == Order.BUYER) coreDataStructureRepository.saveBuyerTraderCompositeByItemId(order.getFutureId(), waitingComposites);
        else coreDataStructureRepository.saveSellerTraderCompositeByItemId(order.getFutureId(), waitingComposites);
    }

    private void insertIntoWaitingQueue(Order order, List<TraderComposite> waitingComposites) {
        int index = binaryFindIndex(order, waitingComposites);
        insertIntoWaitingQueue(order, waitingComposites, index);
    }

    private int binaryFindIndex(Order order, List<TraderComposite> waitingComposites) {
        //TODO 使用二分查找
        for (int i=0; i<waitingComposites.size(); ++i){
            if (waitingComposites.get(i).getPrice() == order.getUnitPrice()) return i;
            else if (waitingComposites.get(i).getPrice() > order.getUnitPrice()) return i;
        }
        return waitingComposites.size();
    }
}
