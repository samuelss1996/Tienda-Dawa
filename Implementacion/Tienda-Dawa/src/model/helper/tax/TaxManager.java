package model.helper.tax;

import model.vo.Product;

import java.util.*;

/**
 * 
 */
public interface TaxManager {

    /**
     * @param product
     */
    void apply(Product product);

    /**
     * @param productList
     */
    void apply(List<? extends Product> productList);

    /**
     * @param price
     */
    float revert(float price);

    /**
     *
     * @param priceWithTaxes
     * @return
     */
    float calculateTaxes(float priceWithTaxes);
}