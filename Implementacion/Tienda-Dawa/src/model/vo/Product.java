package model.vo;

/**
 * 
 */
public class Product {
    private int id;
    private float price;
    private int stock;
    private EProductType type;

    public Product(int id, float price, int stock, int type) {
        this(id, price, stock, EProductType.valueOf(type));
    }

    public Product(int id, float price, int stock, EProductType type) {
        this.id = id;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
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