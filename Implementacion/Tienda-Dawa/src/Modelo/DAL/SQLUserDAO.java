package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public class SQLUserDAO implements UserDAO {

    /**
     * Default constructor
     */
    public SQLUserDAO() {
    }

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