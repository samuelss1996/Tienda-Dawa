package controller;

import controller.helper.StoreHelper;
import model.exception.OutOfStockException;
import model.util.UTFUtils;
import model.vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "store", urlPatterns = "/store")
public class StoreController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoreHelper helper = new StoreHelper(request.getSession(), request);
        HttpSession session = request.getSession();

        switch (UTFUtils.getParameter(request, "action")) {
            case "addToCart":
                try {
                    Product product = parseProduct(request);
                    helper.addToCart(product, Integer.parseInt(UTFUtils.getParameter(request, "quantity")));
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp").forward(request, response);
                } catch (OutOfStockException e) {
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp?error=outOfStockWhenAddToCart").forward(request, response);
                }
                break;
            case "deleteFromCart":
                helper.removeFromCart(Integer.valueOf(UTFUtils.getParameter(request, "lineNumber")));
                this.getServletContext().getRequestDispatcher("/shoppingCart.jsp").forward(request, response);
                break;
            case "changeShopCartQuantity":
                try {
                    helper.updateShopCartQuantity(Integer.valueOf(UTFUtils.getParameter(request, "lineNumber")), Integer.valueOf(UTFUtils.getParameter(request, "quantity")));
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp").forward(request, response);
                } catch (OutOfStockException e) {
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp?error=outOfStockWhenAddToCart").forward(request, response);
                }
                break;
            case "createOrder":
                try {
                    ShopCart shopCart = (ShopCart) session.getAttribute(StoreHelper.SHOPPING_CART);
                    Client client = helper.getClientInfo((String)session.getAttribute("username"));
                    Order order = helper.createOrder(client, shopCart);
                    session.setAttribute("order", order);

                    this.getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
                } catch (OutOfStockException e) {
                    session.setAttribute(StoreHelper.SHOPPING_CART, new ShopCart());
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp?error=outOfStockDuringProcess").forward(request, response);
                }
                break;
            case "confirmOrder":
                try {
                    Order order = (Order) session.getAttribute("order");
                    boolean upgraded = helper.confirmOrder(order);
                    session.setAttribute("order", null);
                    request.setAttribute("order", order);
                    session.setAttribute(StoreHelper.SHOPPING_CART, new ShopCart());

                    String urlToShow = upgraded? "/orderResult.jsp?upgraded=true" : "/orderResult.jsp";
                    this.getServletContext().getRequestDispatcher(urlToShow).forward(request, response);
                } catch (OutOfStockException e) {
                    session.setAttribute(StoreHelper.SHOPPING_CART, new ShopCart());
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp?error=outOfStockDuringProcess").forward(request, response);
                }
                break;

        }
    }

    private Product parseProduct(HttpServletRequest request) {
        return new Product(Integer.parseInt(UTFUtils.getParameter(request, "productId")),
                            UTFUtils.getParameter(request, "productName"),
                            Float.parseFloat(UTFUtils.getParameter(request, "productPrice").replace(",", ".")),
                            Integer.parseInt(UTFUtils.getParameter(request, "productStock")),
                            EProductType.valueOf(UTFUtils.getParameter(request, "productType")));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.processRequest(req, resp);
    }
}