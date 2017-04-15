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
        //TODO: solo lista clientes, lo de administración yo diría que se encargue otro
        String queryString = "SELECT * FROM user JOIN client ON client.id = user.id WHERE LOWER(username) LIKE %" + filter.getName().toLowerCase() + "%";
        if (filter.getEmail() != null)
            queryString = queryString.concat(" AND LOWER(email) LIKE %" + filter.getEmail().toLowerCase() + "%");
        if (filter.getType() != null)
            queryString = queryString.concat(" AND type = " + filter.getType().getId());

        List<User> userList = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryString);

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
     * @param userList
     */
    public void deleteUserAccounts(List<User> userList) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE id=?")) {
                for (User user : userList) {
                    preparedStatement.setInt(1, user.getId());

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
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