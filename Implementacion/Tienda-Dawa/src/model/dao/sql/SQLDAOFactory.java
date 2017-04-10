package model.dao.sql;

import model.dao.*;

import java.sql.Connection;

/**
 * 
 */
public class SQLDAOFactory extends DAOFactory {
    private static final String DRIVER = ""; // TODO
    private static final String DB_URL = ""; // TODO

    /**
     * @return
     */
    public static Connection createConnection() {
        // TODO implement here
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }

    @Override
    public StockDAO getStockDAO() {
        return null;
    }

    @Override
    public OrderDAO getOrderDAO() {
        return null;
    }

    @Override
    public ProductDAO getProductDAO() {
        return null;
    }

    @Override
    public RatingDAO getRatingDAO() {
        return null;
    }
}