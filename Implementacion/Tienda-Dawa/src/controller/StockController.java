package controller;

import controller.helper.StockHelper;
import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.vo.*;

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
                case "search":
                    switch(request.getParameter("type")) {
                        case "ALL":
                            request.setAttribute("results", helper.searchProducts((ProductFilter) request.getAttribute("filter")));
                            break;
                        case "CD":
                            request.setAttribute("results", helper.searchCDs((CDFilter) request.getAttribute("filter")));
                            break;
                        case "CACTUS":
                            request.setAttribute("results", helper.searchCacti((CactusFilter) request.getAttribute("filter")));
                            break;
                    }

                    this.getServletContext().getRequestDispatcher("/searchResults.jsp").forward(request, response);
                    break;
                case "details":
                    Product item = helper.getProductDetails(Integer.parseInt(request.getParameter("productId")),
                            EProductType.valueOf(request.getParameter("type")));
                    request.setAttribute("item", item);
                    this.getServletContext().getRequestDispatcher("/productDetails.jsp").forward(request, response);
                    break;
                case "addRating":
                    String username = (String) request.getSession().getAttribute("username");
                    int itemId = Integer.parseInt(request.getParameter("itemId"));
                    if(username != null && helper.isOwner(username, itemId)) {
                        Comment comment = new Comment(request.getParameter("ratingTitle"),
                                request.getParameter("ratingContent"));
                        Rating rating = new Rating(Integer.parseInt(request.getParameter("ratingValue")),
                                new Product(itemId), new Client(username), comment);
                        helper.addRating(rating);
                    } else {
                        this.getServletContext().getRequestDispatcher("/clientAuth.jsp").forward(request, response);
                    }
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