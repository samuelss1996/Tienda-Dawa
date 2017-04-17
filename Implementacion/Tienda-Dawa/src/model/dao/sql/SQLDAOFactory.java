package model.dao.sql;

import model.dao.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDAOFactory extends DAOFactory {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dawa?useUnicode=true&characterEncoding=UTF-8&noAccessToProcedureBodies=true";

    public static Connection createConnection() {
        try {
            Class.forName(DRIVER).newInstance();
            return DriverManager.getConnection(DB_URL, "dawa", "dawa");
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
    public AdministrationDAO getAdministrationDAO() {
        return new SQLAdministrationDAO();
    }
}