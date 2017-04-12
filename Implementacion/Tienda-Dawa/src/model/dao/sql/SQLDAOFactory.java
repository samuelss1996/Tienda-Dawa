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
        return new SQLUserDAO();
    }

    @Override
    public StockDAO getStockDAO() {
        return new SQLStockDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new SQLOrderDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new SQLProductDAO();
    }

    @Override
    public RatingDAO getRatingDAO() {
        return new SQLRatingDAO();
    }
}