package model.helper.tax;

import model.vo.Product;

import java.util.*;

public interface TaxManager {

    void apply(Product product);

    void apply(List<? extends Product> productList);

    float revert(float price);

    float calculateTaxes(float priceWithTaxes);
}