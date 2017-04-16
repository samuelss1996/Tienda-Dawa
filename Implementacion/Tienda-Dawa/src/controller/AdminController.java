package controller;

import controller.helper.AdminHelper;
import model.filter.ClientFilter;
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
        request.setCharacterEncoding("UTF-8");
        AdminHelper helper = new AdminHelper();

        switch(request.getParameter("action")) {
            case "login":
                if(helper.login(request.getParameter("login-name"), request.getParameter("login-password"))) {
                    request.setAttribute("clientsList", helper.listUserAccounts(new ClientFilter()));
                    request.getSession().setAttribute("adminName", request.getParameter("login-name"));
                    this.getServletContext().getRequestDispatcher("/admin/listUsers.jsp").forward(request, response);
                } else {
                    this.getServletContext().getRequestDispatcher("/admin/adminLogin.jsp?error=wrongLogin").forward(request, response);
                }
                break;
            case "changePassword":
                if(request.getSession().getAttribute("adminName") != null) {
                    request.setAttribute("clientsList", helper.listUserAccounts(new ClientFilter()));

                    if(!request.getParameter("password").trim().isEmpty() && request.getParameter("password").equals(request.getParameter("password-again"))) {
                        helper.changePassword(Integer.valueOf(request.getParameter("clientId")), request.getParameter("password"));
                        this.getServletContext().getRequestDispatcher("/admin/listUsers.jsp?success=changePassword").forward(request, response);
                    } else {
                        this.getServletContext().getRequestDispatcher("/admin/listUsers.jsp?error=changePassword").forward(request, response);
                    }
                }
                break;
            case "deleteAccount":
                if(request.getSession().getAttribute("adminName") != null) {
                    helper.deleteUserAccounts(Integer.valueOf(request.getParameter("clientId")));
                    this.getServletContext().getRequestDispatcher("/admin/listUsers.jsp?success=deleteAccount").forward(request, response);
                }
                break;
            case "listUsers":
                if(request.getSession().getAttribute("adminName") != null) {
                    request.setAttribute("clientsList", helper.listUserAccounts(new ClientFilter()));
                    this.getServletContext().getRequestDispatcher("/admin/listUsers.jsp").forward(request, response);
                }
                break;
            case "listProducts":
                if(request.getSession().getAttribute("adminName") != null) {
                    EProductType listType = EProductType.valueOf(request.getParameter("type"));

                    request.setAttribute("type", listType);
                    request.setAttribute("productsList", helper.listProducts(listType));
                    this.getServletContext().getRequestDispatcher("/admin/listProducts.jsp").forward(request, response);
                }
                break;
            case "addProduct":
                switch(request.getParameter("type")) {
                    case "CD":
                        helper.insert(new CD(-1, Float.valueOf(request.getParameter("price").replace(",", ".")),
                                Integer.valueOf(request.getParameter("stock")), request.getParameter("cdTitle"),
                                request.getParameter("cdAuthor"), Year.parse(request.getParameter("cdYear"))));
                        break;
                    case "CACTUS":
                        helper.insert(new Cactus(-1, Float.valueOf(request.getParameter("price").replace(",", ".")),
                                Integer.valueOf(request.getParameter("stock")), request.getParameter("cactusSpecies"),
                                request.getParameter("cactusOrigin")));
                        break;
                }

                response.sendRedirect(String.format("/administration?action=listProducts&type=%s", request.getParameter("type")));
                break;
            case "editProduct":
                switch(request.getParameter("type")) {
                    case "CD":
                        helper.update(new CD(Integer.valueOf(request.getParameter("productId")), Float.valueOf(request.getParameter("price").replace(",", ".")),
                                Integer.valueOf(request.getParameter("stock")), request.getParameter("cdTitle"),
                                request.getParameter("cdAuthor"), Year.parse(request.getParameter("cdYear"))));
                        break;
                    case "CACTUS":
                        helper.update(new Cactus(Integer.valueOf(request.getParameter("productId")),
                                Float.valueOf(request.getParameter("price").replace(",", ".")), Integer.valueOf(request.getParameter("stock")),
                                request.getParameter("cactusSpecies"), request.getParameter("cactusOrigin")));
                        break;
                }
                response.sendRedirect(String.format("/administration?action=listProducts&type=%s", request.getParameter("type")));
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