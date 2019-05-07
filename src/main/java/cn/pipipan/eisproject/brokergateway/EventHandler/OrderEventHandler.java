package cn.pipipan.eisproject.brokergateway.EventHandler;

import cn.pipipan.eisproject.brokergateway.Core.OrderProcessor;
import cn.pipipan.eisproject.brokergateway.Core.OrderProcessorFactory;
import com.lmax.disruptor.EventHandler;

public class OrderEventHandler implements EventHandler<OrderEvent> {

    @Override
    public void onEvent(OrderEvent orderEvent, long l, boolean b) throws Exception {
        OrderProcessor orderProcessor = OrderProcessorFactory.createOrderProcessor(orderEvent.getOrder());
        orderProcessor.process(orderEvent.getOrder());
    }
}
