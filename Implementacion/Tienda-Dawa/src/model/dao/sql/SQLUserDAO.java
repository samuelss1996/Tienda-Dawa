package model.dao.sql;

import model.dao.UserDAO;
import model.vo.Client;
import model.vo.EClientType;

import java.sql.*;

/**
 * 
 */
public class SQLUserDAO implements UserDAO {

    /**
     * @param client
     */
    public void registerClient(Client client, String password) {
        try (Connection connection = new SQLDAOFactory().createConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user (username, email, password) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, client.getUsername());
                preparedStatement.setString(2, client.getEmail());
                preparedStatement.setString(3, password);

                preparedStatement.executeQuery();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.first())
                    insertAsClient(client, generatedKeys.getInt(1), connection);

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertAsClient(Client client, int clientID, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into client(id, type, totalExpenses) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, client.getType().ordinal());
            preparedStatement.setFloat(3, client.getTotalExpenses());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean clientLogin(String username, String password) {
        Integer userID = new Integer(0);
        return (checkPassword(username, password, userID) && isUser(userID));
    }

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean adminLogin(String username, String password) {
        Integer userID = new Integer(0);
        return (checkPassword(username, password, userID) && isAdmin(userID));
    }

    /**
     * @param userID
     * @param newClientType
     */
    public void upgradeClient(int userID, EClientType newClientType) {
        try (Connection connection = new SQLDAOFactory().createConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery("UPDATE client SET type = " + newClientType.ordinal() + " WHERE id = " + userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param username 
     * @param oldPassword 
     * @param newPassword
     */
    public void changePassword(String username, String oldPassword, String newPassword) {
        if (!checkPassword(username, oldPassword)) return;

        try (Connection connection = new SQLDAOFactory().createConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("UPDATE user SET password = " + newPassword + " WHERE username = " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param username 
     * @param password
     */
    public void deleteAccount(String username, String password) {
        if (!checkPassword(username, password)) return;

        try (Connection connection = new SQLDAOFactory().createConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM user WHERE username = " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUser(int userID) {
        try (Connection connection = new SQLDAOFactory().createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM client WHERE id = ?")) {
                preparedStatement.setInt(1, userID);

                return preparedStatement.executeQuery().first();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isAdmin(int adminID) {
        try (Connection connection = new SQLDAOFactory().createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE id = ?")) {
                preparedStatement.setInt(1, adminID);

                return preparedStatement.executeQuery().first();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkPassword(String username, String password) {
        return checkPassword(username, password, null);
    }

    private boolean checkPassword(String username, String password, Integer returnID) {
        try (Connection connection = new SQLDAOFactory().createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM user WHERE username = ?;", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();

                if (returnID != null) {
                    try (ResultSet generatedKeySet = preparedStatement.getGeneratedKeys()) {
                        if(generatedKeySet.next())
                            returnID = generatedKeySet.getInt(1);
                    }
                }

                return (resultSet.first() && resultSet.getString(1).equals(password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}