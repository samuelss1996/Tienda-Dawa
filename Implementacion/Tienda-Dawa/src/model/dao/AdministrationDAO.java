package model.dao;

import model.filter.ClientFilter;
import model.vo.EProductType;
import model.vo.Product;
import model.vo.User;

import java.util.*;

/**
 * 
 */
public interface AdministrationDAO {

    /**
     * @param from 
     * @param to 
     * @param filter 
     * @return
     */
    List<User> listUserAccounts(int from, int to, ClientFilter filter);

    /**
     * @param userList
     */
    void deleteUserAccounts(List<User> userList);

    /**
     * @param from 
     * @param to 
     * @return
     */
    List<Product> listProducts(int from, int to);

    /**
     * @param from 
     * @param to 
     * @param type 
     * @return
     */
    List<Product> listProducts(int from, int to, EProductType type);

    /**
     * @param userId 
     * @param newPassword
     */
    void changeUserPassword(int userId, String newPassword);
}