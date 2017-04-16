package controller;

import controller.helper.AdminHelper;
import model.filter.ClientFilter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "admin", urlPatterns = "/administration")
public class AdminController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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