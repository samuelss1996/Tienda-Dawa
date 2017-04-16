package controller;

import controller.helper.AdminHelper;

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
                    this.getServletContext().getRequestDispatcher("/admin/listUsers.jsp").forward(request, response);
                } else {
                    this.getServletContext().getRequestDispatcher("/admin/adminLogin.jsp?error=wrongLogin").forward(request, response);
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