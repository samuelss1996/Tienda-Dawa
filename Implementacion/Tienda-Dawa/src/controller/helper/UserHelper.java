package controller.helper;

import model.dao.DAOFactory;
import model.dao.UserDAO;
import model.vo.Client;

public class UserHelper {

    public UserHelper() {
    }

    public void registerClient(Client client, String password) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        userDAO.registerClient(client, password);
    }

    public boolean userLogin(String username, String password) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        return userDAO.clientLogin(username, password);
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        userDAO.changePassword(username, oldPassword, newPassword);
    }

}