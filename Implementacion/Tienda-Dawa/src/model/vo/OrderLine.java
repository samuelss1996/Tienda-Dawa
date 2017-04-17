package model.vo;

public class OrderLine {
    private int lineNumber;
    private Product product;
    private int quantity;
    private float unitPrice;

    public OrderLine(int lineNumber, Product product, int quantity, float unitPrice) {
        this.lineNumber = lineNumber;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //TODO: actualizar el resto del vo para ignorar unitPrice
    public float getUnitPrice() {
        return product.getPrice();
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getLinePrice() { return this.product.getPrice() * this.quantity;}

    public String getLinePriceAsString() { return String.format("%.2f", this.getLinePrice()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderLine orderLine = (OrderLine) o;

        return product != null ? product.equals(orderLine.product) : orderLine.product == null;
    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }
}