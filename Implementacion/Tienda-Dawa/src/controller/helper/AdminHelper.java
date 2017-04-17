package controller.helper;

import model.dao.AdministrationDAO;
import model.dao.DAOFactory;
import model.dao.ProductDAO;
import model.dao.UserDAO;
import model.filter.ClientFilter;
import model.vo.*;

import java.util.*;

public class AdminHelper {

    public AdminHelper() {
    }

    public boolean login(String username, String password) {
        UserDAO userDAO = DAOFactory.getFactory(DAOFactory.SQL).getUserDAO();
        return userDAO.adminLogin(username, password);
    }

    public void insert(CD cd) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        productDAO.insert(cd);
    }

    public void insert(Cactus cactus) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        productDAO.insert(cactus);
    }

    public void update(CD cd) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        productDAO.update(cd);
    }

    public void update(Cactus cactus) {
        ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
        productDAO.update(cactus);
    }

    public List<Product> listProducts() {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(0, 100);
    }

    public List<Product> listProducts(int from, int to) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(from, to);
    }

    public List<Product> listProducts(EProductType type) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(0, 100, type);
    }

    public List<Product> listProducts(int from, int to, EProductType type) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listProducts(from, to, type);
    }

    public List<User> listUserAccounts(ClientFilter filter) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listUserAccounts(0, 100, filter);
    }

    public List<User> listUserAccounts(int from, int to, ClientFilter filter) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        return administrationDAO.listUserAccounts(from, to, filter);
    }

    public void deleteUserAccounts(int userId) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        administrationDAO.deleteUserAccounts(userId);
    }

    public void changePassword(int userId, String newPassword) {
        AdministrationDAO administrationDAO = DAOFactory.getFactory(DAOFactory.SQL).getAdministrationDAO();
        administrationDAO.changeUserPassword(userId, newPassword);
    }

}