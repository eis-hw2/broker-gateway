package cn.pipipan.eisproject.brokergateway.Domain;

import cn.pipipan.eisproject.brokergateway.Core.TraderComposite;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    class Pair{
        int count;
        int unitPrice;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Pair(int count, int unitPrice) {
            this.count = count;
            this.unitPrice = unitPrice;
        }
    }
    List<Pair> buyer = new ArrayList<>();
    List<Pair> seller = new ArrayList<>();

    public OrderBook(List<TraderComposite> buyers, List<TraderComposite> sellers){
        for (int i=buyers.size()-1; i>=Math.max(0, buyers.size()-3); i--){
            TraderComposite traderComposite = buyers.get(i);
            buyer.add(new Pair(traderComposite.getOrders().stream().mapToInt(Order::getCount).sum(), traderComposite.getPrice()));
        }
        for (int i=0; i<Math.min(3, sellers.size()); i++){
            TraderComposite traderComposite = sellers.get(i);
            seller.add(new Pair(traderComposite.getOrders().stream().mapToInt(Order::getCount).sum(), traderComposite.getPrice()));
        }
    }

    public OrderBook() {
    }

    public List<Pair> getBuyer() {
        return buyer;
    }

    public void setBuyer(List<Pair> buyer) {
        this.buyer = buyer;
    }

    public List<Pair> getSeller() {
        return seller;
    }

    public void setSeller(List<Pair> seller) {
        this.seller = seller;
    }
}
