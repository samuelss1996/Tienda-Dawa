package controller.helper;

import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.exception.OutOfStockException;
import model.helper.tax.TaxManager;
import model.helper.tax.TaxManagerFactory;
import model.vo.*;

import javax.servlet.http.HttpSession;

/**
 * 
 */
public class StoreHelper {

    private static final String SHOPPING_CART = "shoppingCart";
    private final HttpSession session;

    /**
     * Default constructor
     */
    public StoreHelper(HttpSession session) {
        this.session = session;

        if (session.getAttribute(SHOPPING_CART) == null)
            session.setAttribute(SHOPPING_CART, new ShopCart());
    }

    /**
     * @param client 
     * @param shopCart 
     * @return
     */
    public Order createOrder(Client client, ShopCart shopCart) throws OutOfStockException {
        OrderDAO orderDAO = DAOFactory.getFactory(DAOFactory.SQL).getOrderDAO();
        Order order = orderDAO.createOrder(client, shopCart);
        for(OrderLine orderLine : order.getLines()) {
            TaxManagerFactory.getTaxManager(session).apply(orderLine.getProduct());
        }
        order.updateFinalPrice();
        return order;
    }

    /**
     * @param order
     */
    public void confirmOrder(Order order) throws OutOfStockException {
        OrderDAO orderDAO = DAOFactory.getFactory(DAOFactory.SQL).getOrderDAO();
        orderDAO.confirmOrder(order);
    }

    /**
     * @param product
     */
    public void addToCart(Product product, int amount) throws OutOfStockException {
        ShopCart shopCart = (ShopCart) session.getAttribute(SHOPPING_CART);
        //TODO: lineNumber
        if (amount > product.getStock()) {
            updateProductDetails(product);
            if (amount > product.getStock())
                throw new OutOfStockException();
        }
        shopCart.add(product, amount);
    }

    /**
     * @param product
     */
    public void removeFromCart(Product product) {
        ShopCart shopCart = (ShopCart) session.getAttribute(SHOPPING_CART);
        shopCart.remove(product);
    }

    private void updateProductDetails(Product product) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        product = productDAO.fetchProduct(product.getId(), product.getType());
    }
}