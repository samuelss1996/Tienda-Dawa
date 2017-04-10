package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public interface UserDAO {

    /**
     * @param client
     */
    public void registerClient(Client client);

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean clientLogin(String username, String password);

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean adminLogin(String username, String password);

    /**
     * @param newClientType
     */
    public void upgradeClient(EClientType newClientType);

    /**
     * @param username 
     * @param oldPassword 
     * @param newPassword
     */
    public void changePassword(String username, String oldPassword, String newPassword);

    /**
     * @param username 
     * @param password
     */
    public void deleteAccount(String username, String password);

}