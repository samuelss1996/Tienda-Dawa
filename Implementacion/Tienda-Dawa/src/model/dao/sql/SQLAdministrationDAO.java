package model.dao.sql;

import model.dao.AdministrationDAO;
import model.filter.ClientFilter;
import model.vo.EProductType;
import model.vo.Product;
import model.vo.User;

import java.util.*;

/**
 * 
 */
public class SQLAdministrationDAO implements AdministrationDAO {

    /**
     * @param from 
     * @param to 
     * @param filter 
     * @return
     */
    public List<User> listUserAccounts(int from, int to, ClientFilter filter) {
        // TODO implement here
        return null;
    }

    /**
     * @param userList
     */
    public void deleteUserAccounts(List<User> userList) {
        // TODO implement here
    }

    /**
     * @param from 
     * @param to 
     * @return
     */
    public List<Product> listProducts(int from, int to) {
        // TODO implement here
        return null;
    }

    /**
     * @param from 
     * @param to 
     * @param type 
     * @return
     */
    public List<Product> listProducts(int from, int to, EProductType type) {
        // TODO implement here
        return null;
    }

    /**
     * @param userId 
     * @param newPassword
     */
    public void changeUserPassword(int userId, String newPassword) {
        // TODO implement here
    }
}