package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public interface StockDAO {

    /**
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(int limit);

    /**
     * @param type 
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(EProductType type, int limit);

    /**
     * @param productList
     */
    public void updateProductsStock(List<Product> productList);

    /**
     * @param filter 
     * @return
     */
    public List<Product> searchProducts(ProductFilter filter);

    /**
     * @param filter 
     * @return
     */
    public List<CD> searchCDs(CDFilter filter);

    /**
     * @param filter 
     * @return
     */
    public List<Cactus> searchCacti(CactusFilter filter);

}