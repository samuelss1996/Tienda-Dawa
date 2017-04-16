package model.dao;

import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import java.util.*;

/**
 * 
 */
public interface StockDAO {

    /**
     * @param limit 
     * @return
     */
    List<Product> listAvailableProducts(int limit);

    /**
     * @param type 
     * @param limit 
     * @return
     */
    List<Product> listAvailableProducts(EProductType type, int limit);

    /**
     * @param productList
     */
    void updateProductsStock(List<Product> productList);

    /**
     * @param filter 
     * @return
     */
    List<Product> searchProducts(ProductFilter filter);

    /**
     * @param filter 
     * @return
     */
    List<CD> searchCDs(CDFilter filter);

    /**
     * @param filter 
     * @return
     */
    List<Cactus> searchCacti(CactusFilter filter);

    boolean isOwner(String username, int productId);
}