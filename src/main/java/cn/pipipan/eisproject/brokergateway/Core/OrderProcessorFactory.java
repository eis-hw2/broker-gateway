package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessorFactory implements ApplicationContextAware {
    Logger log = LoggerFactory.getLogger(OrderProcessorFactory.class);
    ApplicationContext applicationContext;
    public OrderProcessor createOrderProcessor(Order order){
        switch (order.getType()){
            case LIMIT:
                return applicationContext.getBean("LimitOrderProcessor", LimitOrderProcessor.class);
            case MARKET:
                return applicationContext.getBean("MarketOrderProcessor", LimitOrderProcessor.class);
            case STOP:
                return applicationContext.getBean("StopOrderProcessor", LimitOrderProcessor.class);
            case CANCEL:
                return applicationContext.getBean("CancelOrderProcessor", LimitOrderProcessor.class);
        }
        log.error("Wrong Order Type");
        return applicationContext.getBean("NullOrderProcessor", LimitOrderProcessor.class);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
