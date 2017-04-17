package controller;

import controller.helper.UserHelper;
import model.helper.mail.MailAgent;
import model.util.UTFUtils;
import model.vo.Client;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserController extends HttpServlet {

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserHelper helper = new UserHelper();

        switch(UTFUtils.getParameter(request, "action")) {
            case "registerClient":
                if(this.isValidRegisterInput(request)) {
                    try {
                        helper.registerClient(new Client(UTFUtils.getParameter(request, "username"), UTFUtils.getParameter(request, "email")),
                                UTFUtils.getParameter(request, "password"));
                        this.getServletContext().getRequestDispatcher("/clientAuth.jsp?success=register").forward(request, response);
                    } catch (IllegalArgumentException e) {
                        this.getServletContext().getRequestDispatcher("/clientAuth.jsp?error=registerExistingUsername").forward(request, response);
                    }
                } else {
                    this.getServletContext().getRequestDispatcher("/clientAuth.jsp?error=register").forward(request, response);
                }
                break;
            case "loginUser":
                if(this.isValidLoginInput(request)) {
                    if(helper.userLogin(UTFUtils.getParameter(request, "username"), UTFUtils.getParameter(request, "password"))) {
                        request.getSession().setAttribute("username", UTFUtils.getParameter(request, "username"));
                        response.sendRedirect("stock");
                    } else {
                        this.getServletContext().getRequestDispatcher("/clientAuth.jsp?error=wrongLogin").forward(request, response);
                    }
                } else {
                    this.getServletContext().getRequestDispatcher("/clientAuth.jsp?error=login").forward(request, response);
                }
                break;
            case "changePassword":
                if(this.isValidChangePasswordInput(request)) {
                    if(helper.userLogin((String) request.getSession().getAttribute("username"), request.getParameter("old-password"))) {
                        helper.changePassword((String) request.getSession().getAttribute("username"), request.getParameter("old-password"),
                                request.getParameter("new-password"));
                        this.getServletContext().getRequestDispatcher("/clientSettings.jsp?success=changePassword").forward(request, response);
                    } else {
                        this.getServletContext().getRequestDispatcher("/clientSettings.jsp?error=wrongChangePassword").forward(request, response);
                    }
                } else {
                    this.getServletContext().getRequestDispatcher("/clientSettings.jsp?error=changePassword").forward(request, response);
                }
                break;
            case "closeSession":
                request.getSession().invalidate();
                response.sendRedirect("stock?success=closeSession");
                break;
        }
    }

    private boolean isValidLoginInput(HttpServletRequest request) {
        return  !UTFUtils.getParameter(request, "password").trim().equals("") && !UTFUtils.getParameter(request, "username").trim().equals("");
    }

    private boolean isValidRegisterInput(HttpServletRequest request) {
        return  UTFUtils.getParameter(request, "password").equals(request.getParameter("password-again"))
                && !UTFUtils.getParameter(request, "password").trim().equals("") && !UTFUtils.getParameter(request, "username").trim().equals("")
                && MailAgent.isValidEmail(UTFUtils.getParameter(request, "email"));
    }

    private boolean isValidChangePasswordInput(HttpServletRequest request) {
        return !request.getParameter("old-password").trim().isEmpty() && !request.getParameter("new-password").trim().isEmpty()
                && request.getParameter("new-password").equals(request.getParameter("new-password-again"));
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