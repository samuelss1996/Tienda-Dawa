package model.dao.sql;

import model.dao.UserDAO;
import model.util.CryptUtils;
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
        if (existsUser(client.getUsername(), client.getEmail())) throw new IllegalArgumentException("Ya existe un usuario con ese nombre");
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into user (username, email, password) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, client.getUsername());
                preparedStatement.setString(2, client.getEmail());
                preparedStatement.setString(3, CryptUtils.sha512Crypt(password));

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.first())
                    insertAsClient(client, generatedKeys.getInt(1), connection);

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean existsUser(String username, String email) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? OR email = ?")) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, email);

                ResultSet resultSet = preparedStatement.executeQuery();

                return resultSet.first();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void insertAsClient(Client client, int clientID, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into client(id, type, totalExpenses) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, clientID);
            preparedStatement.setInt(2, client.getType().getId());
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
        int userId = checkPassword(username, password);
        return (userId != -1 && isUser(userId));
    }

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean adminLogin(String username, String password) {
        int userId = checkPassword(username, password);
        return (userId != -1 && isAdmin(userId));
    }

    /**
     * @param userID
     * @param newClientType
     */
    public void upgradeClient(int userID, EClientType newClientType) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery("UPDATE client SET type = " + newClientType.getId() + " WHERE id = " + userID);
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
        if (checkPassword(username, oldPassword) == -1) return;

        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE username = ? AND password = ?")) {
                preparedStatement.setString(1, CryptUtils.sha512Crypt(newPassword));
                preparedStatement.setString(2, username);
                preparedStatement.setString(3, CryptUtils.sha512Crypt(oldPassword));

                preparedStatement.executeUpdate();
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
        if (checkPassword(username, password) == -1) return;

        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM user WHERE username = " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isUser(int userID) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
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
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE id = ?")) {
                preparedStatement.setInt(1, adminID);

                return preparedStatement.executeQuery().first();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private int checkPassword(String username, String password) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM user WHERE username = ? AND password = ?;")) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, CryptUtils.sha512Crypt(password));

                ResultSet resultSet = preparedStatement.executeQuery();

                if(resultSet.first()) {
                    return resultSet.getInt("id");
                } else {
                    return -1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Client fetchClient(String username) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user JOIN client ON user.id = client.id WHERE username = ?")) {
                preparedStatement.setString(1, username);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new Client(resultSet.getInt("id"),
                                        resultSet.getString("username"),
                                        resultSet.getString("email"),
                                        resultSet.getDate("signupDate"),
                                        resultSet.getInt("type"),
                                        resultSet.getFloat("totalExpenses"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}