package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public class SQLAdministrationDAO implements AdministrationDAO {

    /**
     * Default constructor
     */
    public SQLAdministrationDAO() {
    }

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