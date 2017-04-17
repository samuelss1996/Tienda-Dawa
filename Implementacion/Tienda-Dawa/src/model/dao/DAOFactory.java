package model.dao;

import com.sun.istack.internal.NotNull;
import model.dao.sql.SQLDAOFactory;

public abstract class DAOFactory {
    public static final int SQL = 1;

    @NotNull
    public static DAOFactory getFactory(int whichFactory) {
        switch (whichFactory) {
            case SQL:
                return new SQLDAOFactory();
            default:
                return null;
        }
    }

    public abstract UserDAO getUserDAO();

    public abstract StockDAO getStockDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract ProductDAO getProductDAO();

    public abstract RatingDAO getRatingDAO();

    public abstract AdministrationDAO getAdministrationDAO();
}