package Controlador;

import java.util.*;

/**
 * 
 */
public class StockHelper {

    /**
     * Default constructor
     */
    public StockHelper() {
    }

    /**
     * @param rating
     */
    public void addRating(Rating rating) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Product> listProducts() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public List<Product> listAvailableProducts() {
        // TODO implement here
        return null;
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
     * @return
     */
    public List<Product> listAvailableProducts(EProductType type) {
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
     * @param productId 
     * @param type 
     * @param session 
     * @return
     */
    public Product getProductDetails(int productId, EProductType type, HttpSession session) {
        // TODO implement here
        return null;
    }

    /**
     * @param filter 
     * @param session 
     * @return
     */
    public List<Product> searchProducts(ProductFilter filter, HttpSession session) {
        // TODO implement here
        return null;
    }

    /**
     * @param filter 
     * @param session 
     * @return
     */
    public List<CD> searchCDs(CDFilter filter, HttpSession session) {
        // TODO implement here
        return null;
    }

    /**
     * @param filter 
     * @param session 
     * @return
     */
    public List<Cactus> searchCacti(CactusFilter filter, HttpSession session) {
        // TODO implement here
        return null;
    }

}