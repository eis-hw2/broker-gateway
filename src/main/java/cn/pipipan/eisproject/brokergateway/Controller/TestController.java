package cn.pipipan.eisproject.brokergateway.Controller;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/test")
    public void test(){
        rabbitTemplate.convertAndSend("exchange", "buyer", 123);
    }

}
