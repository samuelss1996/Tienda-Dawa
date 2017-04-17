package controller;

import controller.helper.StockHelper;
import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.util.UTFUtils;
import model.vo.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "stock", urlPatterns = "/stock")
public class StockController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StockHelper helper = new StockHelper(request);

        if(UTFUtils.getParameter(request, "action") != null) {
            switch (UTFUtils.getParameter(request, "action")) {
                case "search":
                    switch(UTFUtils.getParameter(request, "type")) {
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
                    Product item = helper.getProductDetails(Integer.parseInt(UTFUtils.getParameter(request, "productId")),
                            EProductType.valueOf(UTFUtils.getParameter(request, "type")));
                    List<Rating> ratings = helper.listRatings(item);
                    float averageRating = helper.calculateAverageRating(item);
                    boolean isOwner = request.getSession().getAttribute("username") != null
                            && helper.isOwner((String) request.getSession().getAttribute("username"), item.getId());

                    request.setAttribute("item", item);
                    request.setAttribute("averageRating", String.format("%.1f", averageRating));
                    request.setAttribute("ratings", ratings);
                    request.setAttribute("isOwner", isOwner);

                    this.getServletContext().getRequestDispatcher("/productDetails.jsp").forward(request, response);
                    break;
                case "addRating":
                    String username = (String) request.getSession().getAttribute("username");
                    int itemId = Integer.parseInt(UTFUtils.getParameter(request, "itemId"));
                    if(username != null && helper.isOwner(username, itemId)) {
                        Comment comment = (!UTFUtils.getParameter(request, "ratingTitle").trim().isEmpty() && !UTFUtils.getParameter(request, "ratingContent").trim().isEmpty())?
                                new Comment(UTFUtils.getParameter(request, "ratingTitle"), UTFUtils.getParameter(request, "ratingContent")) : null;
                        Rating rating = new Rating(Integer.parseInt(UTFUtils.getParameter(request, "ratingValue")),
                                new Product(itemId), new Client(username), comment);
                        boolean success = helper.addRating(rating);

                        String status = success? "success=rating" : "error=alreadyRated";
                        response.sendRedirect(String.format("stock?action=details&productId=%d&type=%s&%s", itemId,
                                UTFUtils.getParameter(request, "itemType"), status));
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