package Controller;

import Controller.Helpers.ViewDispatcher;
import Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Cristofer Canosa Domínguez on 05/03/2017.
 */
@WebServlet(name = "UserController", urlPatterns = "/user")
public class UserController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getParameter("actionId")) {
            case "gotoLogin":
                ViewDispatcher.show("login.jsp", request, response);
                break;
            case "loginUser":
                loginUser(request.getParameter("username"),
                          request.getParameter("password"));
                //TODO: should be history-aware
                request.setAttribute("userLogged", true);
                ViewDispatcher.show("checkout.jsp", request, response);
                break;
            case "registerUser":
                register(request.getParameter("username"),
                         request.getParameter("password"),
                         request.getParameter("email"));
                //TODO: same as above
                request.setAttribute("userLogged", true);
                ViewDispatcher.show("checkout.jsp", request, response);
                break;
            default:
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void loginUser(String username, String password) {

    }

    private void register(String username, String password, String email) {

    }
}
