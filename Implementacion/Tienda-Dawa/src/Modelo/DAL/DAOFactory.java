package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public abstract class DAOFactory {

    /**
     * Default constructor
     */
    public DAOFactory() {
    }

    /**
     * 
     */
    public static final int SQL = 1;

    /**
     * @param whichFactory
     */
    public static final void getFactory(int whichFactory) {
        // TODO implement here
    }

    /**
     * @return
     */
    public UserDAO getUserDAO() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public StockDAO getStockDAO() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public OrderDAO getOrderDAO() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public ProductDAO getProductDAO() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public RatingDAO getRatingDAO() {
        // TODO implement here
        return null;
    }

}