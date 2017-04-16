package model.dao.sql;

import model.dao.StockDAO;
import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class SQLStockDAO implements StockDAO {

    /**
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(int limit) {
        List<Product> availableProducts = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE stock > 0 LIMIT ?")) {
                preparedStatement.setInt(1, limit);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                     availableProducts.add(new Product(resultSet.getInt("id"),
                                                       resultSet.getString("name"),
                                                       resultSet.getFloat("price"),
                                                       resultSet.getInt("stock"),
                                                       resultSet.getInt("type")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableProducts;
    }

    /**
     * @param type 
     * @param limit 
     * @return
     */
    public List<Product> listAvailableProducts(EProductType type, int limit) {
        switch (type) {
            case CD:
                return listAvailableCDs(limit);
            case CACTUS:
                return listAvailableCati(limit);
        }
        return null;
    }

    private List<Product> listAvailableCati(int limit) {
        List<Product> availableCacti = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product JOIN cactus ON product.id = cactus.id LIMIT ?")) {
                preparedStatement.setInt(1, limit);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    availableCacti.add(new Cactus(resultSet.getInt("id"),
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
        return availableCacti;
    }

    private List<Product> listAvailableCDs(int limit) {
        List<Product> availableCDs = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product JOIN cd ON product.id = cd.id LIMIT ?")) {
                preparedStatement.setInt(1, limit);

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    availableCDs.add(new CD(resultSet.getInt("id"),
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
        return availableCDs;
    }

    /**
     * @param productList
     */
    public void updateProductsStock(List<Product> productList) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET stock = ? WHERE id = ?")) {
                for(Product product : productList) {
                    preparedStatement.setInt(1, product.getStock());
                    preparedStatement.setInt(2, product.getId());

                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filter 
     * @return
     */
    public List<Product> searchProducts(ProductFilter filter) {
        String queryString = "SELECT * FROM product WHERE LOWER(name) LIKE ? AND (? IS NULL OR price >= ?) AND (? IS NULL OR price <= ?)";

        List<Product> searchResults = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
                preparedStatement.setString(1, "%" + filter.getProductName().toLowerCase() + "%");
                preparedStatement.setObject(2, filter.getMinPrice(), Types.FLOAT);
                preparedStatement.setObject(3, filter.getMinPrice(), Types.FLOAT);
                preparedStatement.setObject(4, filter.getMaxPrice(), Types.FLOAT);
                preparedStatement.setObject(5, filter.getMaxPrice(), Types.FLOAT);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    searchResults.add(new Product(resultSet.getInt("id"),
                                                  resultSet.getString("name"),
                                                  resultSet.getFloat("price"),
                                                  resultSet.getInt("stock"),
                                                  resultSet.getInt("type")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResults;
    }

    /**
     * @param filter 
     * @return
     */
    public List<CD> searchCDs(CDFilter filter) {
        String titleFilter = (filter.getTitle() != null)? "%" + filter.getTitle().toLowerCase() + "%" : null;
        String authorFilter = (filter.getAuthor() != null)? "%" + filter.getAuthor().toLowerCase() + "%" : null;
        Integer minYearFilter = (filter.getMinYear() != null)? filter.getMinYear().getValue() : null;
        Integer maxYearFilter = (filter.getMaxYear() != null)? filter.getMaxYear().getValue() : null;

        String queryString = "SELECT * FROM product JOIN cd ON product.id = cd.id WHERE LOWER(name) LIKE ? " +
                "AND (? IS NULL OR LOWER(cd.title) LIKE ?) AND (? IS NULL OR LOWER(cd.author) LIKE ?) " +
                "AND (? IS NULL OR cd.year >= ?) AND (? IS NULL OR cd.year <= ?) " +
                "AND (? IS NULL OR price >= ?) AND (? IS NULL OR price <= ?)";

        List<CD> searchResults = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
                preparedStatement.setString(1, "%" + filter.getProductName().toLowerCase() + "%");
                preparedStatement.setString(2, titleFilter);
                preparedStatement.setString(3, titleFilter);
                preparedStatement.setString(4, authorFilter);
                preparedStatement.setString(5, authorFilter);
                preparedStatement.setObject(6, minYearFilter);
                preparedStatement.setObject(7, minYearFilter);
                preparedStatement.setObject(8, maxYearFilter);
                preparedStatement.setObject(9, maxYearFilter);
                preparedStatement.setObject(10, filter.getMinPrice(), Types.FLOAT);
                preparedStatement.setObject(11, filter.getMinPrice(), Types.FLOAT);
                preparedStatement.setObject(12, filter.getMaxPrice(), Types.FLOAT);
                preparedStatement.setObject(13, filter.getMaxPrice(), Types.FLOAT);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    searchResults.add(new CD(resultSet.getInt("id"),
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
        return searchResults;
    }

    /**
     * @param filter 
     * @return
     */
    public List<Cactus> searchCacti(CactusFilter filter) {
        String speciesFilter = (filter.getSpecies() != null)? "%" + filter.getSpecies().toLowerCase() + "%" : null;
        String originFilter = (filter.getOrigin() != null)? "%" + filter.getOrigin().toLowerCase() + "%" : null;

        String queryString = "SELECT * FROM product JOIN cactus ON product.id = cactus.id WHERE LOWER(name) LIKE ? " +
                "AND (? IS NULL OR LOWER(cactus.species) LIKE ?) AND (? IS NULL OR LOWER(cactus.origin) LIKE ?) " +
                "AND (? IS NULL OR price >= ?) AND (? IS NULL OR price <= ?)";

        List<Cactus> searchResults = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryString)) {
                preparedStatement.setString(1, "%" + filter.getProductName().toLowerCase() + "%");
                preparedStatement.setString(2, speciesFilter);
                preparedStatement.setString(3, speciesFilter);
                preparedStatement.setString(4, originFilter);
                preparedStatement.setString(5, originFilter);
                preparedStatement.setObject(6, filter.getMinPrice(), Types.FLOAT);
                preparedStatement.setObject(7, filter.getMinPrice(), Types.FLOAT);
                preparedStatement.setObject(8, filter.getMaxPrice(), Types.FLOAT);
                preparedStatement.setObject(9, filter.getMaxPrice(), Types.FLOAT);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    searchResults.add(new Cactus(resultSet.getInt("id"),
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
        return searchResults;
    }
}