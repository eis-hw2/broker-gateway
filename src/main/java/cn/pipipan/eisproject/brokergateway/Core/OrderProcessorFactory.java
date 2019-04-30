package cn.pipipan.eisproject.brokergateway.Core;

import cn.pipipan.eisproject.brokergateway.Domain.Order;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessorFactory implements ApplicationContextAware {
    ApplicationContext applicationContext;
    public OrderProcessor createOrderProcessor(Order order){
        switch (order.getType()){
            case LIMIT:
                return applicationContext.getBean("LimitOrderProcessor", LimitOrderProcessor.class);
            case MARKET:
            case STOP:
            case CANCEL:
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
