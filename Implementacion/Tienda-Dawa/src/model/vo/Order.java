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

    public void updateFinalPrice() {
        finalPrice = 0f;
        for (OrderLine orderLine : lines)
            finalPrice += orderLine.getUnitPrice() * orderLine.getQuantity() * (1 - discount/100.0f);
    }

}