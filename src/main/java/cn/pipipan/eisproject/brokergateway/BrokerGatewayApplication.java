package cn.pipipan.eisproject.brokergateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class BrokerGatewayApplication {
    //TODO 加入CICD
    public static ApplicationContext ac;
    public static void main(String[] args) {
        ac = SpringApplication.run(BrokerGatewayApplication.class, args);
    }

}
