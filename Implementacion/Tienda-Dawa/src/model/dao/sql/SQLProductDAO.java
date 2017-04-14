package model.dao.sql;

import model.dao.ProductDAO;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import javax.rmi.PortableRemoteObject;
import java.sql.*;
import java.time.Year;

/**
 * 
 */
public class SQLProductDAO implements ProductDAO {

    /**
     * @param cd
     */
    public void insert(CD cd) {
        //TODO: mirar si existe algún producto con el mismo nombre
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into product (productName, price, stock, type) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, cd.getProductName());
                preparedStatement.setFloat(2, cd.getPrice());
                preparedStatement.setInt(3, cd.getStock());
                preparedStatement.setInt(4, EProductType.CD.getId());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT into cd (id, title, author, year)")) {
                        preparedStatement.setInt(1, generatedKeys.getInt(1));
                        preparedStatement.setString(2, cd.getTitle());
                        preparedStatement.setString(3, cd.getAuthor());
                        preparedStatement.setInt(4, cd.getYear().getValue()); //TODO: igual no funciona

                        preparedStatement.executeUpdate();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param cactus
     */
    public void insert(Cactus cactus) {
        //TODO: mirar si existe algún producto con el mismo nombre
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into product (productName, price, stock, type) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, cactus.getProductName());
                preparedStatement.setFloat(2, cactus.getPrice());
                preparedStatement.setInt(3, cactus.getStock());
                preparedStatement.setInt(4, EProductType.CACTUS.getId());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT into cactus (id, origin, species) VALUES (?, ?, ?)")) {
                        preparedStatement.setInt(1, generatedKeys.getInt(1));
                        preparedStatement.setString(2, cactus.getOrigin());
                        preparedStatement.setString(3, cactus.getSpecies());

                        preparedStatement.executeUpdate();
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param productId 
     * @param productType 
     * @return
     */
    public Product fetchProduct(int productId, EProductType productType) {
        Product product = null;
        switch (productType) {
            case CD:
                product = fetchCD(productId);
                break;
            case CACTUS:
                product = fetchCactus(productId);
                break;
            default:
        }
        return product;
    }

    private Product fetchBaseProduct(int productId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Product(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getFloat("price"),
                                    resultSet.getInt("stock"),
                                    resultSet.getInt("type"));
            }

        }
        return null;
    }

    private CD fetchCD (int productId) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            Product baseProduct = fetchBaseProduct(productId, connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cd WHERE id = ?")) {
                preparedStatement.setInt(1, productId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    return new CD(baseProduct,
                                  resultSet.getString("title"),
                                  resultSet.getString("author"),
                                  Year.of(resultSet.getInt("year")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Cactus fetchCactus (int productId) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            Product baseProduct = fetchBaseProduct(productId, connection);
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cactus WHERE id = ?")) {
                preparedStatement.setInt(1, productId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    return new Cactus(baseProduct,
                                      resultSet.getString("species"),
                                      resultSet.getString("origin"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}