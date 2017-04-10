package Modelo.Helpers;

import java.util.*;

/**
 * 
 */
public interface TaxManager {

    /**
     * @param product
     */
    public void apply(Product product);

    /**
     * @param productList
     */
    public void apply(List<Product> productList);

}