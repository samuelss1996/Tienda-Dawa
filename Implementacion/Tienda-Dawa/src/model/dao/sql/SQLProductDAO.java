package model.dao.sql;

import model.dao.ProductDAO;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

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
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            if (existsProduct(cd.getProductName(), connection)) throw new IllegalArgumentException("Ya existe un producto con ese nombre");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into product (name, price, stock, type) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, cd.getProductName());
                preparedStatement.setFloat(2, cd.getPrice());
                preparedStatement.setInt(3, cd.getStock());
                preparedStatement.setInt(4, EProductType.CD.getId());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO cd (id, title, author, year) VALUES (?, ?, ?, ?)")) {
                        preparedStatement1.setInt(1, generatedKeys.getInt(1));
                        preparedStatement1.setString(2, cd.getTitle());
                        preparedStatement1.setString(3, cd.getAuthor());
                        preparedStatement1.setInt(4, cd.getYear().getValue());

                        preparedStatement1.executeUpdate();
                    }
                }

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean existsProduct(String productName, Connection connection) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE LOWER(name) = ?")) {
            statement.setString(1, productName.trim().toLowerCase());
            ResultSet resultSet = statement.executeQuery();

            return resultSet.first();
        }
    }

    /**
     * @param cactus
     */
    public void insert(Cactus cactus) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            if (existsProduct(cactus.getProductName(), connection)) throw new IllegalArgumentException("Ya existe un producto con ese nombre");
            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into product (name, price, stock, type) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, cactus.getProductName());
                preparedStatement.setFloat(2, cactus.getPrice());
                preparedStatement.setInt(3, cactus.getStock());
                preparedStatement.setInt(4, EProductType.CACTUS.getId());

                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if(generatedKeys.next()) {
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT into cactus (id, origin, species) VALUES (?, ?, ?)")) {
                        preparedStatement1.setInt(1, generatedKeys.getInt(1));
                        preparedStatement1.setString(2, cactus.getOrigin());
                        preparedStatement1.setString(3, cactus.getSpecies());

                        preparedStatement1.executeUpdate();
                    }
                }

                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CD cd) {
        try(Connection connection = SQLDAOFactory.createConnection()) {
            try {
                connection.setAutoCommit(false);
                this.update(connection, cd);

                String cdQuery = "UPDATE cd SET title = ?, author = ?, year = ? WHERE id = ?";
                try (PreparedStatement cdStatement = connection.prepareStatement(cdQuery)) {
                    cdStatement.setString(1, cd.getTitle());
                    cdStatement.setString(2, cd.getAuthor());
                    cdStatement.setInt(3, cd.getYear().getValue());
                    cdStatement.setInt(4, cd.getId());

                    cdStatement.executeUpdate();
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Cactus cactus) {
        try(Connection connection = SQLDAOFactory.createConnection()) {
            try {
                connection.setAutoCommit(false);
                this.update(connection, cactus);

                String cactusQuery = "UPDATE cactus SET origin = ?, species = ? WHERE id = ?";
                try (PreparedStatement cdStatement = connection.prepareStatement(cactusQuery)) {
                    cdStatement.setString(1, cactus.getOrigin());
                    cdStatement.setString(2, cactus.getSpecies());
                    cdStatement.setInt(3, cactus.getId());

                    cdStatement.executeUpdate();
                    connection.commit();
                }
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void update(Connection connection, Product product) throws SQLException {
        String productQuery = "UPDATE product SET name = ?, price = ?, stock = ? WHERE id = ?";

        try(PreparedStatement productStatement = connection.prepareStatement(productQuery)) {
            productStatement.setString(1, product.getProductName());
            productStatement.setFloat(2, product.getPrice());
            productStatement.setInt(3, product.getStock());
            productStatement.setInt(4, product.getId());

            productStatement.executeUpdate();
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