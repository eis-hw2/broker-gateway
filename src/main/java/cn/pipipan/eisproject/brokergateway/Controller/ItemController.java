package cn.pipipan.eisproject.brokergateway.Controller;

import cn.pipipan.eisproject.brokergateway.Dao.ItemRepository;
import cn.pipipan.eisproject.brokergateway.Domain.Item;
import cn.pipipan.eisproject.brokergateway.Domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Item")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @PostMapping("")
    public Response<Item> addItem(@RequestBody Item item){
        item = itemRepository.save(item);
        return new Response<>(item, 200, "OK");
    }
}
