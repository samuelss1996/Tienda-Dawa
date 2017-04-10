package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public class SQLStockDAO implements StockDAO {

    /**
     * Default constructor
     */
    public SQLStockDAO() {
    }

    /**
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(int limit) {
        // TODO implement here
        return null;
    }

    /**
     * @param type 
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(EProductType type, int limit) {
        // TODO implement here
        return null;
    }

    /**
     * @param productList
     */
    public void updateProductsStock(List<Product> productList) {
        // TODO implement here
    }

    /**
     * @param filter 
     * @return
     */
    public List<Product> searchProducts(ProductFilter filter) {
        // TODO implement here
        return null;
    }

    /**
     * @param filter 
     * @return
     */
    public List<CD> searchCDs(CDFilter filter) {
        // TODO implement here
        return null;
    }

    /**
     * @param filter 
     * @return
     */
    public List<Cactus> searchCacti(CactusFilter filter) {
        // TODO implement here
        return null;
    }

}