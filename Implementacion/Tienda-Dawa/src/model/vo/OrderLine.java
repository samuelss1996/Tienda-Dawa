package model.vo;

/**
 * 
 */
public class OrderLine {
    private int lineNumber;
    private Product product;
    private int quantity;
    private float unitPrice;

    public OrderLine(Product product, int quantity, float unitPrice) {
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

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        return (obj instanceof OrderLine && ((OrderLine) obj).product.getId() == this.product.getId());
    }
}