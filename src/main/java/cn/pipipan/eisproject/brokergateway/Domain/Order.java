package cn.pipipan.eisproject.brokergateway.Domain;

public abstract class Order {
    public static final int LIMIT = 0;
    public static final int MARKET = 1;
    public static final int STOP = 2;
    public static final int CANCEL = 3;
}
