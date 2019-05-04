package cn.pipipan.eisproject.brokergateway.Domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//To simplify the Order Entity, mix all types together
@Document
public class Order {
    public static final int BUYER = 0;
    public static final int SELLER = 1;
    public static enum Type{
        LIMIT,
        MARKET,
        STOP,
        CANCEL
    }
    Type type;
    @Id
    String id;
    String itemId;
    @ApiModelProperty(notes = "0代表买方，1代表卖方")
    int position;
    int price;
    int count;
    //LimitOrder
    //MarketOrder
    //StopOrder
    //CancelOrder


    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
