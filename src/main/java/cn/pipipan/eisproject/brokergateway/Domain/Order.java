package cn.pipipan.eisproject.brokergateway.Domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//To simplify the Order Entity, mix all types together
@Document
public class Order {
    public static final int BUYER = 0;
    public static final int SELLER = 1;

    public void minusCount(int delta) {
        this.count -= delta;
    }

    public static enum Type{
        LIMIT,
        MARKET,
        STOP,
        CANCEL
    }

    public static enum Status{
        PENDING,
        DONE;
    }

    @Id
    String id;
    String futureId;
    Status status = Status.PENDING;
    @ApiModelProperty(notes = "0代表买方，1代表卖方")
    int position;
    int unitPrice;
    int count;
    Type type;
    //LimitOrder
    //MarketOrder
    //StopOrder
    //CancelOrder
    @ApiModelProperty(notes = "准备取消的orderId")
    String orderId;


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

    public String getFutureId() {
        return futureId;
    }

    public void setFutureId(String futureId) {
        this.futureId = futureId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean canAfford(int price){
        return position == Order.SELLER ? this.unitPrice <= price : this.unitPrice >= price;
    }

    public boolean finished() {
        return count <= 0;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
