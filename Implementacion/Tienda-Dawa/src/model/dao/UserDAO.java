package model.dao;

import model.vo.Client;
import model.vo.EClientType;

/**
 * 
 */
public interface UserDAO {

    /**
     * @param client
     */
    void registerClient(Client client, String password);

    /**
     * @param username 
     * @param password 
     * @return
     */
    boolean clientLogin(String username, String password);

    /**
     * @param username 
     * @param password 
     * @return
     */
    boolean adminLogin(String username, String password);

    /**
     * @param newClientType
     */
    void upgradeClient(EClientType newClientType);

    /**
     * @param username 
     * @param oldPassword 
     * @param newPassword
     */
    void changePassword(String username, String oldPassword, String newPassword);

    /**
     * @param username 
     * @param password
     */
    void deleteAccount(String username, String password);
}