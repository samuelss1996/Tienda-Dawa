package controller.helper;

import model.dao.*;
import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.helper.tax.TaxManager;
import model.helper.tax.TaxManagerFactory;
import model.vo.*;

import javax.servlet.ServletRequest;
import java.util.List;

public class StockHelper {
    private final ServletRequest request;

    public StockHelper(ServletRequest request) {
        this.request = request;
    }

    public void addRating(Rating rating) {
        RatingDAO ratingDAO = DAOFactory.getFactory(DAOFactory.SQL).getRatingDAO();
        ratingDAO.addRating(rating);
    }

    public List<Rating> listRatings(Product product) {
        RatingDAO ratingDAO = DAOFactory.getFactory(DAOFactory.SQL).getRatingDAO();
        return ratingDAO.listRatings(product);
    }

    public float calculateAverageRating(Product product) {
        RatingDAO ratingDAO = DAOFactory.getFactory(DAOFactory.SQL).getRatingDAO();
        return ratingDAO.calculateAverageRating(product);
    }

    public List<Product> listProducts() {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        //TODO: limits
        return administrationDAO.listProducts(0, 100);
    }

    public List<Product> listAvailableProducts() {
        return this.listAvailableProducts(null, -1);
    }

    public List<Product> listAvailableProducts(int limit) {
        return this.listAvailableProducts(null, limit);
    }

    public List<Product> listAvailableProducts(EProductType type) {
        return this.listAvailableProducts(type, -1);
    }

    public List<Product> listAvailableProducts(EProductType type, int limit) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<Product> result;
        limit = (limit == -1)? 100 : limit;

        if(type != null) {
            result = stockDAO.listAvailableProducts(type, limit);
        } else {
            result = stockDAO.listAvailableProducts(limit);
        }
        //TODO: limit
        TaxManagerFactory.getTaxManager(this.request).apply(result);
        return result;
    }

    public Product getProductDetails(int productId, EProductType type) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        Product product = productDAO.fetchProduct(productId, type);
        TaxManager taxManager = TaxManagerFactory.getTaxManager(this.request);
        taxManager.apply(product);
        return product;
    }

    public List<Product> searchProducts(ProductFilter filter) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<Product> results = stockDAO.searchProducts(filter);
        TaxManager taxManager = TaxManagerFactory.getTaxManager(this.request);
        taxManager.apply(results);
        return results;
    }

    public List<CD> searchCDs(CDFilter filter) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<CD> results = stockDAO.searchCDs(filter);
        TaxManager taxManager = TaxManagerFactory.getTaxManager(this.request);
        taxManager.apply(results);
        return results;
    }

    public List<Cactus> searchCacti(CactusFilter filter) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        List<Cactus> results = stockDAO.searchCacti(filter);
        TaxManager taxManager = TaxManagerFactory.getTaxManager(this.request);
        taxManager.apply(results);
        return results;
    }

    public boolean isOwner(String username, int itemId) {
        StockDAO stockDAO = DAOFactory.getFactory(DAOFactory.SQL).getStockDAO();
        return stockDAO.isOwner(username, itemId);
    }
}