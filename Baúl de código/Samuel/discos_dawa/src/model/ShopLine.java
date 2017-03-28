package model;

public class ShopLine {
    private CDProduct product;
    private Integer quantity;
    private Float totalPrize;

    public ShopLine(CDProduct product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;

        this.totalPrize = product.getPrize() * quantity;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
        this.totalPrize += quantity * this.product.getPrize();
    }

    public CDProduct getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getTotalPrize() {
        return totalPrize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShopLine shopLine = (ShopLine) o;

        return product != null ? product.equals(shopLine.product) : shopLine.product == null;
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }
}