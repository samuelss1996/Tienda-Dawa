package model.vo;

import java.util.*;

/**
 * 
 */
public class Order {
    private int id;
    private Client client;
    private List<OrderLine> lines;
    private float finalPrice;
    private Date date;
    private float discount;

    public Order() {
        this.lines = new ArrayList<>();
    }

    public void addLine(OrderLine line) {
        lines.add(line);
        finalPrice += line.getUnitPrice() * line.getQuantity() * (1 - discount/100.0f);
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public float getBasePrice() { return this.finalPrice * (1 + discount/100.0f); }

    public String getBasePriceAsString() { return String.format("%.2f", this.getBasePrice()); }

    public String getFinalPriceAsString() { return String.format("%.2f", this.finalPrice); }

    public void updateFinalPrice() {
        finalPrice = 0f;
        for (OrderLine orderLine : lines)
            finalPrice += orderLine.getUnitPrice() * orderLine.getQuantity() * (1 - discount/100.0f);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getFullDiscount() { return this.getBasePrice() - this.finalPrice; }

    public String getFullDiscountAsString() { return String.format("%.2f", this.getFullDiscount()); }

}