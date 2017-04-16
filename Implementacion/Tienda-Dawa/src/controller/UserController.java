package controller;

import controller.helper.UserHelper;
import model.helper.mail.MailAgent;
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
        request.setCharacterEncoding("UTF-8");
        UserHelper helper = new UserHelper();

        switch(request.getParameter("action")) {
            case "registerClient":
                if(this.isValidRegisterInput(request)) {
                    helper.registerClient(new Client(request.getParameter("username"), request.getParameter("email")),
                            request.getParameter("password"));
                    this.getServletContext().getRequestDispatcher("/clientAuth.jsp?success=register").forward(request, response);
                } else {
                    this.getServletContext().getRequestDispatcher("/clientAuth.jsp?error=register").forward(request, response);
                }
                break;
            case "loginUser": // TODO comprobar si ya tenía el carrito con ítems antes de hacer el login
                if(this.isValidLoginInput(request)) {
                    if(helper.userLogin(request.getParameter("username"), request.getParameter("password"))) {
                        request.getSession().setAttribute("username", request.getParameter("username"));
                        response.sendRedirect("/stock");
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
                response.sendRedirect("/stock?success=closeSession");
                break;
        }
    }

    private boolean isValidLoginInput(HttpServletRequest request) {
        return  !request.getParameter("password").trim().equals("") && !request.getParameter("username").trim().equals("");
    }

    private boolean isValidRegisterInput(HttpServletRequest request) {
        return  request.getParameter("password").equals(request.getParameter("password-again"))
                && !request.getParameter("password").trim().equals("") && !request.getParameter("username").trim().equals("")
                && MailAgent.isValidEmail(request.getParameter("email"));
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