package model.vo;

import java.util.*;

public class ShopCart {
    private final List<OrderLine> lines;
    private float totalPrice;

    public ShopCart() {
        lines = new ArrayList<>();
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int add(Product product, int amount) {
        OrderLine orderLine = new OrderLine(this.lines.size(), product, amount, product.getPrice());

        if (lines.contains(orderLine)) {
            OrderLine oldLine = lines.get(lines.indexOf(orderLine));
            oldLine.setQuantity(oldLine.getQuantity() + orderLine.getQuantity());
        } else {
            lines.add(orderLine);
        }
        totalPrice += orderLine.getUnitPrice() * orderLine.getQuantity();

        return this.lines.size() - 1;
    }

    public OrderLine remove(int lineNumber) {
        OrderLine removedLine = this.lines.remove(lineNumber);
        this.totalPrice -= removedLine.getUnitPrice() * removedLine.getQuantity();

        return removedLine;
    }

    public void reduceQuantity(int lineNumber, int reductionAmount) {
        if(reductionAmount >= this.lines.get(lineNumber).getQuantity()) {
            this.remove(lineNumber);
        } else {
            this.lines.get(lineNumber).setQuantity(this.lines.get(lineNumber).getQuantity() - reductionAmount);
            this.totalPrice -= this.lines.get(lineNumber).getUnitPrice() * reductionAmount;
        }
    }

    public int getSize() {
        int items = 0;
        for (OrderLine line : lines) {
            items += line.getQuantity();
        }
        return items;
    }

    public String getTotalPriceAsString() {
        return String.format("%.2f", this.totalPrice);
    }
}