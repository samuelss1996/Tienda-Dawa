package controller;

import controller.helper.StoreHelper;
import model.exception.OutOfStockException;
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
        request.setCharacterEncoding("UTF-8");
        StoreHelper helper = new StoreHelper(request.getSession(), request);

        switch (request.getParameter("action")) {
            case "addToCart":
                try {
                    Product product = parseProduct(request);
                    helper.addToCart(product, Integer.parseInt(request.getParameter("quantity")));
                    this.getServletContext().getRequestDispatcher("/shoppingCart.jsp").forward(request, response);
                    //TODO: volver a la pagina que lo llamo
                } catch (OutOfStockException e) {
                    e.printStackTrace();
                }
                break;
            case "createOrder":
                try {
                    HttpSession session = request.getSession();
                    ShopCart shopCart = (ShopCart) session.getAttribute(StoreHelper.SHOPPING_CART);
                    Client client = helper.getClientInfo((String)session.getAttribute("username"));
                    Order order = helper.createOrder(client, shopCart);
                    request.setAttribute("order", order);
                    this.getServletContext().getRequestDispatcher("/checkout.jsp").forward(request, response);
                } catch (OutOfStockException e) {
                    e.printStackTrace();
                }
        }
    }

    private Product parseProduct(HttpServletRequest request) {
        return new Product(Integer.parseInt(request.getParameter("productId")),
                            request.getParameter("productName"),
                            Float.parseFloat(request.getParameter("productPrice").replace(",", ".")),
                            Integer.parseInt(request.getParameter("productStock")),
                            EProductType.valueOf(request.getParameter("productType")));
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