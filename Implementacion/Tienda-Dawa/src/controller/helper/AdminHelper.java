package controller.helper;

import model.dao.AdministrationDAO;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import model.filter.ClientFilter;
import model.vo.*;

import java.util.*;

/**
 * 
 */
public class AdminHelper {

    /**
     * Default constructor
     */
    public AdminHelper() {
    }

    /**
     * @param username 
     * @param password
     */
    public void login(String username, String password) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        userDAO.adminLogin(username, password);
    }

    /**
     * @param cd
     */
    public void insert(CD cd) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        productDAO.insert(cd);
    }

    /**
     * @param cactus
     */
    public void insert(Cactus cactus) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        productDAO.insert(cactus);
    }

    /**
     * @return
     */
    public List<Product> listProducts() {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(0, 100);
    }

    /**
     * @param from 
     * @param to 
     * @return
     */
    public List<Product> listProducts(int from, int to) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(from, to);
    }

    /**
     * @param type 
     * @return
     */
    public List<Product> listProducts(EProductType type) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(0, 100, type);
    }

    /**
     * @param from 
     * @param to 
     * @param type 
     * @return
     */
    public List<Product> listProducts(int from, int to, EProductType type) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(from, to, type);
    }

    /**
     * @param filter 
     * @return
     */
    public List<User> listUserAccounts(ClientFilter filter) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listUserAccounts(0, 100, filter);
    }

    /**
     * @param from 
     * @param to 
     * @param filter 
     * @return
     */
    public List<User> listUserAccounts(int from, int to, ClientFilter filter) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listUserAccounts(from, to, filter);
    }

    /**
     * @param userList
     */
    public void deleteUserAccounts(List<User> userList) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        administrationDAO.deleteUserAccounts(userList);
    }

    /**
     * @param userId
     * @param newPassword
     */
    public void changePassword(int userId, String newPassword) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        administrationDAO.changeUserPassword(userId, newPassword);
    }

}