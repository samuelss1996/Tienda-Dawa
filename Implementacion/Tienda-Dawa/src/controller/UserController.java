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
        }
    }

    private boolean isValidRegisterInput(HttpServletRequest request) {
        return  request.getParameter("password").equals(request.getParameter("password-again"))
                && !request.getParameter("password").trim().equals("") && !request.getParameter("username").trim().equals("")
                && MailAgent.isValidEmail(request.getParameter("email"));
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