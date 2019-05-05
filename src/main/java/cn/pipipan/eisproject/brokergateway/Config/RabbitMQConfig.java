package cn.pipipan.eisproject.brokergateway.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Exchange exchange(){
        return new DirectExchange("exchange");
    }

    @Bean
    public Queue buyerQueue(){
        //可以通过arguments来设置死信队列
        Map<String, Object> args = new HashMap<>();
        args.put("x-expires", 1800000);
        args.put("x-dead-letter-exchange", "dlx_exchange");
        Queue queue = new Queue("buyerQueue", true, false, false, args);
        return queue;
    }

    @Bean
    public Queue sellerQueue(){
        //可以通过arguments来设置死信队列
        Map<String, Object> args = new HashMap<>();
        args.put("x-expires", 1800000);
        args.put("x-dead-letter-exchange", "dlx_exchange");
        Queue queue = new Queue("sellerQueue", true, false, false, args);
        return queue;
    }

    @Bean
    public Binding buyerBinding() {
        return BindingBuilder.bind(buyerQueue()).to(exchange()).with("buyer").noargs();
    }
    @Bean
    public Binding sellerBinding() {
        return BindingBuilder.bind(sellerQueue()).to(exchange()).with("seller").noargs();
    }

    @Bean
    public Exchange dlxExchange(){
        return new TopicExchange("dlx_exchange");
    }

    @Bean Queue dlxQueue(){
        return new Queue("dlx_queue");
    }

    @Bean
    public Binding dlxBinding(){
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("*").noargs();
    }
}
