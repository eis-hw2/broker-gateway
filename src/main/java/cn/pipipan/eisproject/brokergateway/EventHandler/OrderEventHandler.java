package cn.pipipan.eisproject.brokergateway.EventHandler;

import cn.pipipan.eisproject.brokergateway.Core.OrderProcessor;
import cn.pipipan.eisproject.brokergateway.Core.OrderProcessorFactory;
import com.lmax.disruptor.EventHandler;

public class OrderEventHandler implements EventHandler<OrderEvent> {
    private OrderProcessorFactory orderProcessorFactory;

    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        OrderProcessor orderProcessor = orderProcessorFactory.createOrderProcessor(orderEvent.getOrder());
        orderProcessor.process(orderEvent.getOrder());
    }

    public void setOrderProcessorFactory(OrderProcessorFactory orderProcessorFactory) {
        this.orderProcessorFactory = orderProcessorFactory;
    }
}
