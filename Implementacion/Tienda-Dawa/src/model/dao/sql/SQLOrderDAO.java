package model.dao.sql;

import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.exception.OutOfStockException;
import model.helper.discount.DiscountManager;
import model.vo.*;

/**
 * 
 */
public class SQLOrderDAO implements OrderDAO {

    /**
     * @param client 
     * @param shopCart 
     * @return
     */
    public Order createOrder(Client client, ShopCart shopCart) throws OutOfStockException {
        Order order = new Order();
        int lineNumber = 0;

        order.setClient(client);
        order.setDiscount(DiscountManager.getDiscount(client));

        for (OrderLine orderLine : shopCart.getLines()) {
            int quantity = orderLine.getQuantity();
            ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
            Product product = productDAO.fetchProduct(orderLine.getProduct().getId(), orderLine.getProduct().getType());
            if (quantity > product.getStock())
                throw new OutOfStockException();

            order.addLine(new OrderLine(lineNumber, product, quantity, product.getPrice()));
            lineNumber ++;
        }
        return order;
    }

    /**
     * @param order
     */
    public void confirmOrder(Order order) {
        // TODO implement here
    }

}