package controller.helper;

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
        // TODO implement here
    }

    /**
     * @param cd
     */
    public void insert(CD cd) {
        // TODO implement here
    }

    /**
     * @param cactus
     */
    public void insert(Cactus cactus) {
        // TODO implement here
    }

    /**
     * @return
     */
    public List<Product> listProducts() {
        // TODO implement here
        return null;
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
     * @param type 
     * @return
     */
    public List<Product> listProducts(EProductType type) {
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
     * @param filter 
     * @return
     */
    public List<User> listUserAccounts(ClientFilter filter) {
        // TODO implement here
        return null;
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
     * @param username 
     * @param newPassword
     */
    public void changePassword(String username, String newPassword) {
        // TODO implement here
    }

}