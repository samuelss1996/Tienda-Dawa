package model.dao;

import model.vo.Client;
import model.vo.EClientType;

public interface UserDAO {

    void registerClient(Client client, String password);

    boolean clientLogin(String username, String password);

    boolean adminLogin(String username, String password);

    void changePassword(String username, String oldPassword, String newPassword);

    Client fetchClient(String username);
}