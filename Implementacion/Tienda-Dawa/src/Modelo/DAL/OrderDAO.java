package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public interface OrderDAO {

    /**
     * @param client 
     * @param shopCart 
     * @return
     */
    public Order createOrder(Client client, ShopCart shopCart);

    /**
     * @param order
     */
    public void confirmOrder(Order order);

}