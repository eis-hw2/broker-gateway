package cn.pipipan.eisproject.brokergateway.Config;

import cn.pipipan.eisproject.brokergateway.Core.OrderProcessorFactory;
import cn.pipipan.eisproject.brokergateway.EventHandler.OrderEvent;
import cn.pipipan.eisproject.brokergateway.EventHandler.OrderEventFactory;
import cn.pipipan.eisproject.brokergateway.EventHandler.OrderEventHandler;
import cn.pipipan.eisproject.brokergateway.EventHandler.OrderEventProducerWithTranslator;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisruptorConfig {
    @Bean
    OrderEventFactory orderEventFactory(){
        return new OrderEventFactory();
    }

    @Bean
    EventHandler<OrderEvent> orderEventHandler(OrderProcessorFactory orderProcessorFactory){
        return new OrderEventHandler();
    }

    @Bean
    OrderEventProducerWithTranslator orderEventProducerWithTranslator(Disruptor disruptor){
        return new OrderEventProducerWithTranslator(disruptor.getRingBuffer());
    }

    @Bean
    Disruptor<OrderEvent> disruptor(OrderEventFactory orderEventFactory, EventHandler<OrderEvent> orderEventHandler){
        Disruptor<OrderEvent> orderEventDisruptor = new Disruptor<OrderEvent>(orderEventFactory, 1024*1024, DaemonThreadFactory.INSTANCE);
        orderEventDisruptor.handleEventsWith(orderEventHandler);
        orderEventDisruptor.start();
        return orderEventDisruptor;
    }
}
