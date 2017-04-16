package model.dao.sql;

import model.dao.AdministrationDAO;
import model.filter.ClientFilter;
import model.util.CryptUtils;
import model.vo.*;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class SQLAdministrationDAO implements AdministrationDAO {

    /**
     * @param from 
     * @param to 
     * @param filter 
     * @return
     */
    public List<User> listUserAccounts(int from, int to, ClientFilter filter) {
        String emailFilter = (filter.getEmail() != null)? filter.getEmail().toLowerCase() : null;
        Integer typeFilter = (filter.getType() != null)? filter.getType().getId() : null;

        String queryString = "SELECT * FROM user JOIN client ON client.id = user.id WHERE LOWER(username) LIKE ? " +
                "AND (? IS NULL OR LOWER(email) LIKE ?) AND (? IS NULL OR type = ?)";

        List<User> userList = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
                preparedStatement.setString(1, "%" + filter.getName().toLowerCase() + "%");
                preparedStatement.setString(2, emailFilter);
                preparedStatement.setString(3, "%" + emailFilter + "%");
                preparedStatement.setObject(4, typeFilter, Types.INTEGER);
                preparedStatement.setObject(5, typeFilter, Types.INTEGER);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    userList.add(new Client(resultSet.getInt("id"),
                                            resultSet.getString("username"),
                                            resultSet.getString("email"),
                                            resultSet.getDate("signupDate"),
                                            resultSet.getInt("type"),
                                            resultSet.getFloat("totalExpenses")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    /**
     * @param userId
     */
    public void deleteUserAccounts(int userId) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
                preparedStatement.setInt(1, userId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param from 
     * @param to 
     * @return
     */
    public List<Product> listProducts(int from, int to) {
        List<Product> resultList = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product LIMIT ?, ?")) {
                preparedStatement.setInt(1, from);
                preparedStatement.setInt(2, to-from);

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    resultList.add(new Product(resultSet.getInt("id"),
                                                resultSet.getString("name"),
                                                resultSet.getFloat("price"),
                                                resultSet.getInt("stock"),
                                                resultSet.getInt("type")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * @param from 
     * @param to 
     * @param type 
     * @return
     */
    public List<Product> listProducts(int from, int to, EProductType type) {
        switch (type) {
            case CD:
                return listCDs(from, to);
            case CACTUS:
                return listCacti(from ,to);
        }
        return new ArrayList<>();
    }

    private List<Product> listCacti(int from, int to) {
        List<Product> resultList = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product JOIN cactus ON product.id = cactus.id LIMIT ?, ?")) {
                preparedStatement.setInt(1, from);
                preparedStatement.setInt(2, to-from);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    resultList.add(new Cactus(resultSet.getInt("id"),
                                                resultSet.getFloat("price"),
                                                resultSet.getInt("stock"),
                                                EProductType.valueOf(resultSet.getInt("type")),
                                                resultSet.getString("species"),
                                                resultSet.getString("origin")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private List<Product> listCDs(int from, int to) {
        List<Product> resultList = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product JOIN cd ON product.id = cd.id LIMIT ?, ?")) {
                preparedStatement.setInt(1, from);
                preparedStatement.setInt(2, to-from);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    resultList.add(new CD(resultSet.getInt("id"),
                                            resultSet.getFloat("price"),
                                            resultSet.getInt("stock"),
                                            EProductType.valueOf(resultSet.getInt("type")),
                                            resultSet.getString("title"),
                                            resultSet.getString("author"),
                                            Year.of(resultSet.getInt("year"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * @param userId 
     * @param newPassword
     */
    public void changeUserPassword(int userId, String newPassword) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = ? WHERE id = ?")) {
                preparedStatement.setString(1, CryptUtils.sha512Crypt(newPassword));
                preparedStatement.setInt(2, userId);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}