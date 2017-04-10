package model.dao.sql;

import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.dao.StockDAO;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import java.util.*;

/**
 * 
 */
public class SQLStockDAO implements StockDAO {

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