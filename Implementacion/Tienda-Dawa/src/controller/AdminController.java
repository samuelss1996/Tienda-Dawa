package controller;

import controller.helper.AdminHelper;
import model.filter.ClientFilter;
import model.util.UTFUtils;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Year;

@WebServlet(name = "admin", urlPatterns = "/administration")
public class AdminController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminHelper helper = new AdminHelper();

        switch(UTFUtils.getParameter(request, "action")) {
            case "login":
                if(helper.login(request.getParameter("login-name"), request.getParameter("login-password"))) {
                    request.getSession().invalidate();
                    request.setAttribute("clientsList", helper.listUserAccounts(new ClientFilter()));
                    request.getSession().setAttribute("adminName", request.getParameter("login-name"));
                    this.getServletContext().getRequestDispatcher("/adminListUsers.jsp").forward(request, response);
                } else {
                    this.getServletContext().getRequestDispatcher("/adminLogin.jsp?error=wrongLogin").forward(request, response);
                }
                break;
            case "changePassword":
                if(request.getSession().getAttribute("adminName") != null) {
                    request.setAttribute("clientsList", helper.listUserAccounts(new ClientFilter()));

                    if(!UTFUtils.getParameter(request, "password").trim().isEmpty() && UTFUtils.getParameter(request, "password").equals(request.getParameter("password-again"))) {
                        helper.changePassword(Integer.valueOf(UTFUtils.getParameter(request, "clientId")), UTFUtils.getParameter(request, "password"));
                        this.getServletContext().getRequestDispatcher("/adminListUsers.jsp?success=changePassword").forward(request, response);
                    } else {
                        this.getServletContext().getRequestDispatcher("/adminListUsers.jsp?error=changePassword").forward(request, response);
                    }
                }
                break;
            case "deleteAccount":
                if(request.getSession().getAttribute("adminName") != null) {
                    helper.deleteUserAccounts(Integer.valueOf(UTFUtils.getParameter(request, "clientId")));
                    this.getServletContext().getRequestDispatcher("/adminListUsers.jsp?success=deleteAccount").forward(request, response);
                }
                break;
            case "listUsers":
                if(request.getSession().getAttribute("adminName") != null) {
                    request.setAttribute("clientsList", helper.listUserAccounts(new ClientFilter()));
                    this.getServletContext().getRequestDispatcher("/adminListUsers.jsp").forward(request, response);
                }
                break;
            case "listProducts":
                if(request.getSession().getAttribute("adminName") != null) {
                    EProductType listType = EProductType.valueOf(UTFUtils.getParameter(request, "type"));

                    request.setAttribute("type", listType);
                    request.setAttribute("productsList", helper.listProducts(listType));
                    this.getServletContext().getRequestDispatcher("/adminListProducts.jsp").forward(request, response);
                }
                break;
            case "addProduct":
                try {
                    switch (UTFUtils.getParameter(request, "type")) {
                        case "CD":
                            helper.insert(new CD(-1, Float.valueOf(UTFUtils.getParameter(request, "price").replace(",", ".")),
                                    Integer.valueOf(UTFUtils.getParameter(request, "stock")), UTFUtils.getParameter(request, "cdTitle"),
                                    UTFUtils.getParameter(request, "cdAuthor"), Year.parse(UTFUtils.getParameter(request, "cdYear"))));
                            break;
                        case "CACTUS":
                            helper.insert(new Cactus(-1, Float.valueOf(UTFUtils.getParameter(request, "price").replace(",", ".")),
                                    Integer.valueOf(UTFUtils.getParameter(request, "stock")), UTFUtils.getParameter(request, "cactusSpecies"),
                                    UTFUtils.getParameter(request, "cactusOrigin")));
                            break;
                    }

                    response.sendRedirect(String.format("administration?action=listProducts&type=%s", UTFUtils.getParameter(request, "type")));
                } catch (IllegalArgumentException e) {
                    response.sendRedirect(String.format("adminListProducts.jsp?error=insertError", UTFUtils.getParameter(request, "type")));
                }
                break;
            case "editProduct":
                switch(UTFUtils.getParameter(request, "type")) {
                    case "CD":
                        helper.update(new CD(Integer.valueOf(UTFUtils.getParameter(request, "productId")), Float.valueOf(UTFUtils.getParameter(request, "price").replace(",", ".")),
                                Integer.valueOf(UTFUtils.getParameter(request, "stock")), UTFUtils.getParameter(request, "cdTitle"),
                                UTFUtils.getParameter(request, "cdAuthor"), Year.parse(UTFUtils.getParameter(request, "cdYear"))));
                        break;
                    case "CACTUS":
                        helper.update(new Cactus(Integer.valueOf(UTFUtils.getParameter(request, "productId")),
                                Float.valueOf(UTFUtils.getParameter(request, "price").replace(",", ".")), Integer.valueOf(UTFUtils.getParameter(request, "stock")),
                                UTFUtils.getParameter(request, "cactusSpecies"), UTFUtils.getParameter(request, "cactusOrigin")));
                        break;
                }
                response.sendRedirect(String.format("administration?action=listProducts&type=%s", UTFUtils.getParameter(request, "type")));
                break;
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