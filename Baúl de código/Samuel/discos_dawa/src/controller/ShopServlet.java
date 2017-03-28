package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "shop", urlPatterns = "/shop")
public class ShopServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response, request.getParameter("action"));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response, request.getParameter("action"));
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, String action) throws ServletException, IOException {
        ShopHelper shopHelper = new ShopHelper(request.getSession());

        switch(action) {
            case "add-to-cart":
                shopHelper.addToCart(request.getParameter("cd-name"), Integer.valueOf(request.getParameter("cd-quantity")));
                this.displayView(request, response, "shop-cart.jsp");
                break;
            case "remove-from-cart":
                shopHelper.removeFromCart(Integer.valueOf(request.getParameter("cd-index")));
                this.displayView(request, response, "shop-cart.jsp");
                break;
            case "finish-shop-cart":
                shopHelper.finishShopCart(request.getParameter("name"), request.getParameter("email"));
                this.displayView(request, response, "confirmation.jsp");
                break;
            case "finish-shopping":
                shopHelper.finishShopping();
                this.displayView(request, response, "index.jsp");
        }
    }

    private void displayView(HttpServletRequest request, HttpServletResponse response, String viewUrl) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewUrl);
        dispatcher.forward(request, response);
    }
}