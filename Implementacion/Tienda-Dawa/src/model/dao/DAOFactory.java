package model.dao;

import model.dao.sql.SQLDAOFactory;

public abstract class DAOFactory {
    public static final int SQL = 1;

    /**
     * @param whichFactory
     */
    public static final DAOFactory getFactory(int whichFactory) {
        switch (whichFactory) {
            case SQL:
                return new SQLDAOFactory();
            default:
                return null;
        }
    }

    /**
     * @return
     */
    public abstract UserDAO getUserDAO();

    /**
     * @return
     */
    public abstract StockDAO getStockDAO();

    /**
     * @return
     */
    public abstract OrderDAO getOrderDAO();

    /**
     * @return
     */
    public abstract ProductDAO getProductDAO();

    /**
     * @return
     */
    public abstract RatingDAO getRatingDAO();
}