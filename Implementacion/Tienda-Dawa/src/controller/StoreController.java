package controller;

import controller.helper.StoreHelper;
import model.exception.OutOfStockException;
import model.vo.EProductType;
import model.vo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "store", urlPatterns = "/store")
public class StoreController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StoreHelper helper = new StoreHelper(request.getSession(), request);

        switch (request.getParameter("action")) {
            case "addToCart":
                try {
                    Product product = parseProduct(request);
                    helper.addToCart(product, Integer.parseInt(request.getParameter("quantity")));
                    //TODO: volver a la pagina que lo llamo
                } catch (OutOfStockException e) {
                    e.printStackTrace();
                }
        }
    }

    private Product parseProduct(HttpServletRequest request) {
        return new Product(Integer.parseInt(request.getParameter("productId")),
                            request.getParameter("productName"),
                            Float.parseFloat(request.getParameter("productPrice")),
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