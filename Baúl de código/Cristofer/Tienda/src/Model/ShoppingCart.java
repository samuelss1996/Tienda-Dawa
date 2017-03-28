package Model;

import java.util.*;

/**
 * Created by Cristofer Canosa Domínguez on 04/03/2017.
 */
public class ShoppingCart {
    private List<Product> products;
    private float totalPrice;

    public ShoppingCart() {
        this.products = new LinkedList<>();
        totalPrice = 0;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        if (this.products.contains(product)) {
            products.get(products.indexOf(product)).addOrder(product.getReservedQuantity());
        } else {
            this.products.add(product);
        }
        totalPrice += (product.getPrice() * product.getReservedQuantity());
    }

    public void remove(String productTitle) {
        Optional<Product> productOptional = this.products.stream().filter(p -> p.getTitle().equals(productTitle)).findFirst();
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            this.products.remove(product);
            totalPrice -= product.getPrice() * product.getReservedQuantity();
        }
        //TODO: else report error
    }

    public float getTotalPrice() {
        return totalPrice;
    }
}
