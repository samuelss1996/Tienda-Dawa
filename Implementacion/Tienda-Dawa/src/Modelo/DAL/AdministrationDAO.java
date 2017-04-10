package Modelo.DAL;

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
    public List<User> listUserAccounts(int from, int to, ClientFilter filter);

    /**
     * @param userList
     */
    public void deleteUserAccounts(List<User> userList);

    /**
     * @param from 
     * @param to 
     * @return
     */
    public List<Product> listProducts(int from, int to);

    /**
     * @param from 
     * @param to 
     * @param type 
     * @return
     */
    public List<Product> listProducts(int from, int to, EProductType type);

    /**
     * @param userId 
     * @param newPassword
     */
    public void changeUserPassword(int userId, String newPassword);

}