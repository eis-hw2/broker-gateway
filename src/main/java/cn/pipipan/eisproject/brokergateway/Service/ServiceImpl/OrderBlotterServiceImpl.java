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
    public void sealOneDeal(Order bigOrder, Order smallOrder, int price) {
        OrderBlotter orderBlotter = OrderBlotter.createOrderBlotter(bigOrder, smallOrder, price);
        bigOrder.setCount(bigOrder.getCount() - smallOrder.getCount());
        System.out.println(orderBlotter);
        //orderBlotterRepository.save(orderBlotter);
    }
}
