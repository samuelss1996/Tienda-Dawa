package model.filter;

public class ProductFilter {
    private String productName;
    private Float minPrice;
    private Float maxPrice;

    public ProductFilter(String productName) {
        this.productName = productName;
    }

    public ProductFilter(String productName, Float minPrice, Float maxPrice) {
        this.productName = productName;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Float minPrice) {
        this.minPrice = minPrice;
    }

    public Float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Float maxPrice) {
        this.maxPrice = maxPrice;
    }
}