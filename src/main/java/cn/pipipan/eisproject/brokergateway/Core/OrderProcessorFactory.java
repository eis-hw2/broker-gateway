package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.BrokerGatewayApplication;
import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessorFactory{
    public static OrderProcessor createOrderProcessor(Order order){
        switch (order.getType()){
            case LIMIT:
                return BrokerGatewayApplication.ac.getBean("LimitOrderProcessor", LimitOrderProcessor.class);
            case MARKET:
                return BrokerGatewayApplication.ac.getBean("MarketOrderProcessor", MarketOrderProcessor.class);
            case STOP:
                return BrokerGatewayApplication.ac.getBean("StopOrderProcessor", StopOrderProcessor.class);
            case CANCEL:default:
                return BrokerGatewayApplication.ac.getBean("CancelOrderProcessor", CancelOrderProcessor.class);
        }
    }
}
