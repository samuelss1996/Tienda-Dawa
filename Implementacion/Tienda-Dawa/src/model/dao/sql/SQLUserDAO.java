package model.dao.sql;

import model.dao.UserDAO;
import model.vo.Client;
import model.vo.EClientType;

/**
 * 
 */
public class SQLUserDAO implements UserDAO {

    /**
     * @param client
     */
    public void registerClient(Client client) {
        // TODO implement here
    }

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean clientLogin(String username, String password) {
        // TODO implement here
        return false;
    }

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean adminLogin(String username, String password) {
        // TODO implement here
        return false;
    }

    /**
     * @param newClientType
     */
    public void upgradeClient(EClientType newClientType) {
        // TODO implement here
    }

    /**
     * @param username 
     * @param oldPassword 
     * @param newPassword
     */
    public void changePassword(String username, String oldPassword, String newPassword) {
        // TODO implement here
    }

    /**
     * @param username 
     * @param password
     */
    public void deleteAccount(String username, String password) {
        // TODO implement here
    }

}