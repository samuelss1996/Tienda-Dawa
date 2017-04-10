package model.dao.sql;

import model.dao.OrderDAO;
import model.vo.Client;
import model.vo.Order;
import model.vo.ShopCart;

/**
 * 
 */
public class SQLOrderDAO implements OrderDAO {

    /**
     * @param client 
     * @param shopCart 
     * @return
     */
    public Order createOrder(Client client, ShopCart shopCart) {
        // TODO implement here
        return null;
    }

    /**
     * @param order
     */
    public void confirmOrder(Order order) {
        // TODO implement here
    }

}