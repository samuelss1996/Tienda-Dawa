package model.helper.tax;

import model.vo.Product;

import java.util.List;

/**
 *
 */
public class SpainTaxManager implements TaxManager {

    @Override
    public void apply(Product product) {
        product.setPrice(product.getPrice() * 1.21f);
    }

    @Override
    public void apply(List<? extends Product> productList) {
        for(Product product : productList) {
            this.apply(product);
        }
    }

    @Override
    public float revert(float price) {
        return price / 1.21f;
    }

    @Override
    public float calculateTaxes(float priceWithTaxes) {
        return priceWithTaxes - (priceWithTaxes / 1.21f);
    }
}