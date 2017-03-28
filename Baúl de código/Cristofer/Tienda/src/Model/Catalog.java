package Model;

import java.util.*;

/**
 * Created by Cristofer Canosa Domínguez on 04/03/2017.
 */
public class Catalog {
    private List<Product> products;

    public Catalog() {
        this.products = new LinkedList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    private void buyAll(List<Product> products) {

    }
}
