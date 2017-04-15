package model.helper.tax;

import model.vo.Product;

import java.util.List;

/**
 * 
 */
public class UKTaxManager implements TaxManager {

    @Override
    public void apply(Product product) {
        product.setPrice(product.getPrice() * 1.2f);
    }

    @Override
    public void apply(List<? extends Product> productList) {
        for(Product product : productList) {
            this.apply(product);
        }
    }
}