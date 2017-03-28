package Controller;

import Controller.Helpers.ViewDispatcher;
import Model.Product;
import Model.ShoppingCart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * Created by Cristofer Canosa Domínguez on 05/03/2017.
 */
@WebServlet(name = "StoreController", urlPatterns = "/store")
public class StoreController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("actionId")) {
            case "addItem":
                Product product = getSelectedItem(request);
                addToCart(product, request);
                ViewDispatcher.show("shoppingCart.jsp", request, response);
                break;
            case "removeItem":
                String productTitle = request.getParameter("cdTitle");
                remove(productTitle, request);
                ViewDispatcher.show("shoppingCart.jsp", request, response);
                break;
            case "keepShopping":
                ViewDispatcher.show("index.jsp", request, response);
                break;
            case "gotoCheckout":
                ViewDispatcher.show("checkout.jsp", request, response);
                break;
            case "confirmBuy":
                confirmBuy(request);
                ViewDispatcher.show("index.jsp", request, response);
                break;
            default:
                ViewDispatcher.show("index.jsp", request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private Product getSelectedItem(HttpServletRequest request) {
        String cdInfo = request.getParameter("cdInfo");
        StringTokenizer stringTokenizer = new StringTokenizer(cdInfo, "|");
        //TODO: Get from catalog, with correct availableQuantity
        Product product = new Product(
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            stringTokenizer.nextToken(),
            Float.parseFloat(stringTokenizer.nextToken().replace('$',' ').trim()),
            1000
        );
        product.addOrder(Integer.parseInt(request.getParameter("quantity")));
        return product;
    }

    private void confirmBuy(HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) currentSession.getAttribute("shoppingCart");
        //TODO: update catalog in model
        currentSession.setAttribute("shoppingCart", new ShoppingCart());
    }

    private void remove(String productTitle, HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) currentSession.getAttribute("shoppingCart");
        if (shoppingCart != null) {
            shoppingCart.remove(productTitle);
            //currentSession.setAttribute("shoppingCart", shoppingCart);
        }
    }

    private void addToCart(Product product, HttpServletRequest request) {
        HttpSession currentSession = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) currentSession.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        shoppingCart.add(product);
        currentSession.setAttribute("shoppingCart", shoppingCart);
    }
}
