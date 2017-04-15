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
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT TOP ? * FROM product WHERE stock > 0")) {
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
        String queryString = "SELECT * FROM product WHERE LOWER(name) LIKE %" + filter.getProductName().toLowerCase() + "%";
        if (filter.getMinPrice() != null)
            queryString = queryString.concat(" AND price > " + filter.getMinPrice());
        if (filter.getMaxPrice() != null)
            queryString = queryString.concat(" AND price < " + filter.getMaxPrice());

        List<Product> searchResults = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryString);

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
        String queryString = "SELECT * FROM product " +
                                 "JOIN cd ON product.id = cd.id " +
                                 "WHERE LOWER(name) LIKE %" + filter.getProductName().toLowerCase() + "%";
        if (filter.getTitle() != null)
            queryString = queryString.concat(" AND LOWER(cd.title) LIKE %" + filter.getTitle().toLowerCase() + "%");
        if (filter.getAuthor() != null)
            queryString = queryString.concat(" AND LOWER(cd.author) LIKE %" + filter.getAuthor().toLowerCase() + "%");
        if (filter.getMinYear() != null)
            queryString = queryString.concat(" AND cd.year > " + filter.getMinYear());
        if (filter.getMaxYear() != null)
            queryString = queryString.concat(" AND cd.year < " + filter.getMaxYear());
        if (filter.getMinPrice() != null)
            queryString = queryString.concat(" AND price > " + filter.getMinPrice());
        if (filter.getMaxPrice() != null)
            queryString = queryString.concat(" AND price < " + filter.getMaxPrice());

        List<CD> searchResults = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryString);

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
        String queryString = "SELECT * FROM product " +
                                "JOIN cactus ON product.id = cactus.id " +
                                "WHERE LOWER(name) LIKE %" + filter.getProductName().toLowerCase() + "%";
        if (filter.getSpecies() != null)
            queryString = queryString.concat(" AND LOWER(cactus.species) LIKE %" + filter.getSpecies().toLowerCase() + "%");
        if (filter.getOrigin() != null)
            queryString = queryString.concat(" AND LOWER(cactus.origin) LIKE %" + filter.getOrigin().toLowerCase() + "%");
        if (filter.getMinPrice() != null)
            queryString = queryString.concat(" AND price > " + filter.getMinPrice());
        if (filter.getMaxPrice() != null)
            queryString = queryString.concat(" AND price < " + filter.getMaxPrice());

        List<Cactus> searchResults = new ArrayList<>();
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(queryString);

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