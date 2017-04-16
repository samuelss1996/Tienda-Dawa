package model.vo;

/**
 * 
 */
public class Product {
    private int id;
    private String productName;
    private float price;
    private int stock;
    private EProductType type;

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, String productName, float price, int stock, int type) {
        this(id, productName, price, stock, EProductType.valueOf(type));
    }

    public Product(int id, String productName, float price, int stock, EProductType type) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPriceAsString() {
        return String.format("%.2f", this.price);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public EProductType getType() {
        return type;
    }

    public void setType(EProductType type) {
        this.type = type;
    }
}