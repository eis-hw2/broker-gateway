package cn.pipipan.eisproject.brokergateway.Controller;

import cn.pipipan.eisproject.brokergateway.Core.OrderProcessorFactory;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import cn.pipipan.eisproject.brokergateway.Domain.Response;
import cn.pipipan.eisproject.brokergateway.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Order")
public class OrderController {
    Logger log = LoggerFactory.getLogger(OrderController.class);
    @Autowired
    OrderService orderService;
    @Autowired
    OrderProcessorFactory orderProcessorFactory;

    @PostMapping("")
    public Response<Order> postOrder(@RequestBody Order order){
        orderService.setOrderProcessor(orderProcessorFactory.createOrderProcessor(order));
        return new Response(orderService.process(order), 200, "OK");
    }
}
