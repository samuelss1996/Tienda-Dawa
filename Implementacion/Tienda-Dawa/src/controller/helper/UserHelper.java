package controller.helper;

import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.UserDAO;
import model.vo.Client;
import model.vo.User;

/**
 * 
 */
public class UserHelper {

    /**
     * Default constructor
     */
    public UserHelper() {
    }

    /**
     * @param client
     * @param password
     */
    public void registerClient(Client client, String password) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        userDAO.registerClient(client, password);
    }

    /**
     * @param username 
     * @param password
     */
    public void userLogin(String username, String password) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        userDAO.clientLogin(username, password);
    }

    /**
     * @param username 
     * @param oldPassword 
     * @param newPassword
     */
    public void changePassword(String username, String oldPassword, String newPassword) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        userDAO.changePassword(username, oldPassword, newPassword);
    }

}