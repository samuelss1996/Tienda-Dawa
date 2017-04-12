package controller.helper;

import model.dao.*;
import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.helper.tax.TaxManager;
import model.helper.tax.TaxManagerFactory;
import model.vo.*;

import javax.servlet.http.HttpSession;
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
        RatingDAO ratingDAO = DAOFactory.getFactory(DAOFactory.SQL).getRatingDAO();
        ratingDAO.addRating(rating);
    }

    /**
     * @return
     */
    public List<Product> listProducts() {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        //TODO: limits
        return administrationDAO.listProducts(0, 100);
    }

    /**
     * @return
     */
    public List<Product> listAvailableProducts() {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        //TODO: limits
        return stockDAO.listAvailableProducts(100);
    }

    /**
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(int limit) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        return stockDAO.listAvailableProducts(limit);
    }

    /**
     * @param type 
     * @return
     */
    public List<Product> listAvailableProducts(EProductType type) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        //TODO: limit
        return stockDAO.listAvailableProducts(type, 100);
    }

    /**
     * @param type 
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(EProductType type, int limit) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        //TODO: limit
        return stockDAO.listAvailableProducts(type, limit);
    }

    /**
     * @param productId 
     * @param type 
     * @param session 
     * @return
     */
    public Product getProductDetails(int productId, EProductType type, HttpSession session) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        Product product = productDAO.fetchProduct(productId, type);
        TaxManager taxManager = new TaxManagerFactory().getTaxManager(session);
        taxManager.apply(product);
        return product;
    }

    /**
     * @param filter 
     * @param session 
     * @return
     */
    public List<Product> searchProducts(ProductFilter filter, HttpSession session) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<Product> results = stockDAO.searchProducts(filter);
        TaxManager taxManager = new TaxManagerFactory().getTaxManager(session);
        taxManager.apply(results);
        return results;
    }

    /**
     * @param filter 
     * @param session 
     * @return
     */
    public List<CD> searchCDs(CDFilter filter, HttpSession session) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<CD> results = stockDAO.searchCDs(filter);
        TaxManager taxManager = new TaxManagerFactory().getTaxManager(session);
        taxManager.apply(results);
        return results;
    }

    /**
     * @param filter 
     * @param session 
     * @return
     */
    public List<Cactus> searchCacti(CactusFilter filter, HttpSession session) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<Cactus> results = stockDAO.searchCacti(filter);
        TaxManager taxManager = new TaxManagerFactory().getTaxManager(session);
        taxManager.apply(results);
        return results;
    }

}