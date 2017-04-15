package controller;

import controller.helper.StockHelper;
import model.vo.EProductType;
import model.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (name = "stock", urlPatterns = "/stock")
public class StockController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StockHelper helper = new StockHelper(request);

        if(request.getParameter("action") != null) {
            switch (request.getParameter("action")) {
                case "details":
                    Product item = helper.getProductDetails(Integer.parseInt(request.getParameter("productId")),
                            EProductType.valueOf(Integer.parseInt(request.getParameter("type"))),
                            request.getSession());
                    request.setAttribute("item", item);
                    this.getServletContext().getRequestDispatcher("/productDetails.jsp").forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("cds", helper.listAvailableProducts(EProductType.CD));
            request.setAttribute("cacti", helper.listAvailableProducts(EProductType.CACTUS));

            this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
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