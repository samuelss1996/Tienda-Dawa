package model.dao;

public abstract class DAOFactory {
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