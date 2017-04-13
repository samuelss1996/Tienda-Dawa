package model.dao.sql;

import model.dao.ProductDAO;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import javax.rmi.PortableRemoteObject;
import java.sql.*;

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
        // TODO implement here
        return null;
    }

}