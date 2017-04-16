package model.dao.sql;

import model.dao.RatingDAO;
import model.vo.*;

import java.sql.*;
import java.util.*;

/**
 * 
 */
public class SQLRatingDAO implements RatingDAO {

    /**
     * @param rating
     */
    public void addRating(Rating rating) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                String sqlStatement = String.format("INSERT into rating (product, client, value) VALUES (%d, %d, %f)",
                                                    rating.getProduct().getId(), rating.getClient().getId(), rating.getValue());
                statement.executeUpdate(sqlStatement, Statement.RETURN_GENERATED_KEYS);


                if (rating.getComment() != null) {
                    ResultSet generatedKeys = statement.getGeneratedKeys();
                    if (generatedKeys.first())
                        attachComment(rating, generatedKeys.getInt(1), connection);
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

    private void attachComment(Rating rating, int ratingID, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String sqlStatement = String.format("INSERT into COMMENT (rating, title, content) values ( %d, %s, %s)",
                                            ratingID, rating.getComment().getTitle(), rating.getComment().getContent());

            statement.executeUpdate(sqlStatement);
        }
    }

    /**
     * @param product
     */
    public List<Rating> listRatings(Product product) {
        List<Rating> ratingList = new ArrayList<>();

        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT rating.*, comment.*, product.*, client.* " +
                                                                                        "FROM rating " +
                                                                                        "JOIN comment ON comment.rating = rating.id " +
                                                                                        "JOIN product ON product.id = rating.product " +
                                                                                        "JOIN client ON client.id = rating.client")) {
                ResultSet resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    ratingList.add(new Rating(resultSet.getInt("rating.id"),
                                              resultSet.getFloat("rating.value"),
                                              resultSet.getDate("rating.date"),
                                              new Product(resultSet.getInt("product.id"),
                                                          resultSet.getString("product.name"),
                                                          resultSet.getFloat("product.price"),
                                                          resultSet.getInt("product.stock"),
                                                          resultSet.getInt("product.type")),
                                              fetchClient(resultSet.getInt("client.id"),
                                                         Integer.parseInt(resultSet.getString("client.type")),
                                                         resultSet.getFloat("client.totalExpenses")),
                                              new Comment(resultSet.getString("comment.title"),
                                                          resultSet.getString("comment.content"))));
                }

                return ratingList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratingList;
    }

    private Client fetchClient(int userId, int eClientType, float totalExpenses) {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id = " + userId);
                if (resultSet.next())
                    return new Client(userId,
                                        resultSet.getString("username"),
                                        resultSet.getString("email"),
                                        resultSet.getDate("signupDate"),
                                        eClientType, totalExpenses);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}