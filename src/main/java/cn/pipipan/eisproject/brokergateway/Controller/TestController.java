package cn.pipipan.eisproject.brokergateway.Controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @GetMapping("/test")
    public void test(){
        rabbitTemplate.convertAndSend("exchange", "buyer", 123);
    }

    @GetMapping("/refresh")
    public void refresh(){
        //TODO 清空内存
    }

}
