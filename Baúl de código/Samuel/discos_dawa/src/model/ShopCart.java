package model;

import java.util.ArrayList;
import java.util.List;

public class ShopCart {
    private List<ShopLine> shopLineList;
    private Float totalPrize;

    public ShopCart() {
        this.shopLineList = new ArrayList<>();
        this.totalPrize = 0f;
    }

    public void add(ShopLine shopLine) {
        if(this.shopLineList.contains(shopLine)) {
            this.shopLineList.get(this.shopLineList.indexOf(shopLine)).addQuantity(shopLine.getQuantity());
        } else {
            this.shopLineList.add(shopLine);
        }

        this.totalPrize += shopLine.getTotalPrize();
    }

    public void remove(int index) {
        ShopLine shopLine = this.shopLineList.get(index);

        this.shopLineList.remove(index);
        this.totalPrize -= shopLine.getTotalPrize();
    }

    public void clear() {
        this.shopLineList.clear();
        this.totalPrize = 0f;
    }

    public ShopLine getShopLine(Integer index) {
        return this.shopLineList.get(index);
    }

    public List<ShopLine> getShopLineList() {
        return shopLineList;
    }

    public Float getTotalPrize() {
        return totalPrize;
    }
}