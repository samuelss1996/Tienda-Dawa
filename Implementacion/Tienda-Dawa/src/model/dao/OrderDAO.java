package model.dao;

import model.exception.OutOfStockException;
import model.vo.Client;
import model.vo.Order;
import model.vo.ShopCart;

/**
 * 
 */
public interface OrderDAO {

    /**
     * @param client 
     * @param shopCart 
     * @return
     */
    Order createOrder(Client client, ShopCart shopCart) throws OutOfStockException;

    /**
     * @param order
     */
    void confirmOrder(Order order);
}