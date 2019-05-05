package cn.pipipan.eisproject.brokergateway.Config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public Exchange exchange(){
        return new DirectExchange("exchange");
    }

    @Bean
    public Queue buyerQueue(){
        return new Queue("buyerQueue");
    }

    @Bean
    public Queue sellerQueue(){
        return new Queue("sellerQueue");
    }

    @Bean
    public Binding buyerBinding() {
        return BindingBuilder.bind(buyerQueue()).to(exchange()).with("buyer").noargs();
    }
    @Bean
    public Binding sellerBinding() {
        return BindingBuilder.bind(sellerQueue()).to(exchange()).with("seller").noargs();
    }
}
