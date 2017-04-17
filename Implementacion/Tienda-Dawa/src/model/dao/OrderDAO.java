package model.dao;

import model.exception.OutOfStockException;
import model.vo.Client;
import model.vo.Order;
import model.vo.ShopCart;

public interface OrderDAO {

    Order createOrder(Client client, ShopCart shopCart) throws OutOfStockException;

    boolean confirmOrder(Order order) throws OutOfStockException;
}