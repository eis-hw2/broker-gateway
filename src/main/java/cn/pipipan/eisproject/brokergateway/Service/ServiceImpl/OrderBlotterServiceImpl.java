package cn.pipipan.eisproject.brokergateway.Service.ServiceImpl;

import cn.pipipan.eisproject.brokergateway.Dao.OrderBlotterRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Domain.OrderBlotter;
import cn.pipipan.eisproject.brokergateway.Service.OrderBlotterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBlotterServiceImpl implements OrderBlotterService {
    @Autowired
    OrderBlotterRepository orderBlotterRepository;

    @Override
    //TODO 完成一笔交易并通知StopOrder队列 通过比对price和order来确定买方成交还是卖方成交
    public void sealOneDeal(Order order1, Order order2, int price) {
        if (order1.getPosition() == order2.getPosition()) return;
        OrderBlotter orderBlotter = OrderBlotter.createOrderBlotter(order1, order2, price);
        int delta = Math.min(order1.getCount(), order2.getCount());
        order1.minusCount(delta);
        order2.minusCount(delta);
        System.out.println(orderBlotter);
        //orderBlotterRepository.save(orderBlotter);
    }
}
