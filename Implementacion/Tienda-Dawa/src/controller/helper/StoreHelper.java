package controller.helper;

import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import model.exception.OutOfStockException;
import model.helper.tax.TaxManagerFactory;
import model.vo.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

public class StoreHelper {
    public static final String SHOPPING_CART = "shoppingCart";
    private final HttpSession session;
    private final ServletRequest request;

    public StoreHelper(HttpSession session, ServletRequest request) {
        this.session = session;
        this.request = request;

        if (session.getAttribute(SHOPPING_CART) == null)
            session.setAttribute(SHOPPING_CART, new ShopCart());
    }

    public Order createOrder(Client client, ShopCart shopCart) throws OutOfStockException {
        OrderDAO orderDAO = DAOFactory.getFactory(DAOFactory.SQL).getOrderDAO();
        Order order = orderDAO.createOrder(client, shopCart);
        for(OrderLine orderLine : order.getLines()) {
            TaxManagerFactory.getTaxManager(this.request).apply(orderLine.getProduct());
        }
        order.updateFinalPrice();
        return order;
    }

    public boolean confirmOrder(Order order) throws OutOfStockException {
        OrderDAO orderDAO = DAOFactory.getFactory(DAOFactory.SQL).getOrderDAO();
        boolean upgraded = orderDAO.confirmOrder(order);

        order.setDate(new Date(System.currentTimeMillis())); // TODO esto igual es muy cutre de dios
        order.setTaxesCost(TaxManagerFactory.getTaxManager(this.request).calculateTaxes(order.getPriceWithoutDiscount()));

        return upgraded;
    }

    public void addToCart(Product product, int amount) throws OutOfStockException {
        ShopCart shopCart = (ShopCart) session.getAttribute(SHOPPING_CART);

//        if (amount > product.getStock()) {
//
//                throw new OutOfStockException();
//        }

        int newLineNumber = shopCart.add(product, amount);

        if(shopCart.getLines().get(newLineNumber).getQuantity() > product.getStock()) {
            shopCart.reduceQuantity(newLineNumber, amount);
            throw new OutOfStockException();
        }
    }

    public void removeFromCart(int lineNumber) {
        ShopCart shopCart = (ShopCart) session.getAttribute(SHOPPING_CART);
        shopCart.remove(lineNumber);
    }

    public void updateShopCartQuantity(int lineNumber, int newQuantity) throws OutOfStockException {
        ShopCart shopCart = (ShopCart) this.session.getAttribute(SHOPPING_CART);
        int oldQuantity = shopCart.getLines().get(lineNumber).getQuantity();

        if(newQuantity > oldQuantity) {
            this.addToCart(shopCart.getLines().get(lineNumber).getProduct(), newQuantity - oldQuantity);
        } else if(oldQuantity > newQuantity) {
            shopCart.reduceQuantity(lineNumber, oldQuantity - newQuantity);
        }
    }

    private void updateProductDetails(Product product) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        product = productDAO.fetchProduct(product.getId(), product.getType());
    }

    public Client getClientInfo(String username) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        return userDAO.fetchClient(username);
    }
}