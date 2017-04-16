package model.vo;

import java.util.*;

/**
 * 
 */
public class ShopCart {
    private List<OrderLine> lines;
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

    public void add(Product product, int amount) {
        OrderLine orderLine = new OrderLine(0, product, amount, product.getPrice());

        if (lines.contains(orderLine)) {
            OrderLine oldLine = lines.get(lines.indexOf(orderLine));
            oldLine.setQuantity(oldLine.getQuantity() + orderLine.getQuantity());
        } else {
            lines.add(orderLine);
        }
        totalPrice += orderLine.getUnitPrice() * orderLine.getQuantity();
    }

    public void remove(Product product) {
        Optional<OrderLine> productOptional = lines.stream().filter(p -> p.getProduct().equals(product)).findAny();
        if (productOptional.isPresent()) {
            lines.remove(productOptional.get());
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