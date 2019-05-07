package cn.pipipan.eisproject.brokergateway.Controller;

import cn.pipipan.eisproject.brokergateway.Dao.CoreDataStructureRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    CoreDataStructureRepository coreDataStructureRepository;

    @GetMapping("/test")
    public void test(){
        rabbitTemplate.convertAndSend("exchange", "buyer", 123);
    }

    @GetMapping("/refresh")
    public void refresh(){
        coreDataStructureRepository.clear();
    }

    @GetMapping("/restore")
    public void restore(){
        //TODO restore the core list
    }
}
