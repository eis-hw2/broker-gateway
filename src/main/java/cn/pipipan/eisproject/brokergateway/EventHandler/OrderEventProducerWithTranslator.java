package cn.pipipan.eisproject.brokergateway.EventHandler;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

public class OrderEventProducerWithTranslator {
    private final RingBuffer<OrderEvent> ringBuffer;

    public OrderEventProducerWithTranslator(RingBuffer<OrderEvent> ringBuffer)
    {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<OrderEvent, Order> TRANSLATOR =
            new EventTranslatorOneArg<OrderEvent, Order>()
            {
                public void translateTo(OrderEvent event, long sequence, Order order)
                {
                    event.set(order);
                }
            };

    public void onData(Order order)
    {
        ringBuffer.publishEvent(TRANSLATOR, order);
    }
}
