package model.dao;

import model.ShopCart;
import model.ShopLine;
import model.User;

import java.sql.*;

public class OrdersDao {
    private static final OrdersDao INSTANCE = new OrdersDao();

    private OrdersDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static OrdersDao getInstance() {
        return INSTANCE;
    }

    public void insertOrder(ShopCart shopCart, User user) {
        Connection connection = null;

        try {
            connection = this.getConnection();
            connection.setAutoCommit(false);

            int orderId = this.insertOrderUpdateOrdersTable(connection, shopCart, user);
            this.insertOrderUpdateOrdersLinesTable(connection, orderId, shopCart);

            connection.commit();
        } catch (SQLException e) {
            if(connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    private int insertOrderUpdateOrdersTable(Connection connection, ShopCart shopCart, User user) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("INSERT INTO orders(userEmail, userName, totalPrize) VALUES (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, user.getEmail());
        statement.setString(2, user.getName());
        statement.setFloat(3, shopCart.getTotalPrize());

        statement.executeUpdate();

        ResultSet keysResultSet = statement.getGeneratedKeys();
        keysResultSet.next();

        return keysResultSet.getInt(1);
    }

    private void insertOrderUpdateOrdersLinesTable(Connection connection, int orderId, ShopCart shopCart) throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement("INSERT INTO orders_lines(orderId, cdTitle, cdArtist, cdCountry, unitPrize, quantity, totalPrize)" +
                        " VALUES (?, ?, ?, ?, ?, ?, ?)");
        statement.setInt(1, orderId);

        for(ShopLine shopLine : shopCart.getShopLineList()) {
            statement.setString(2, shopLine.getProduct().getTitle());
            statement.setString(3, shopLine.getProduct().getArtist());
            statement.setString(4, shopLine.getProduct().getCountry());
            statement.setFloat(5, shopLine.getProduct().getPrize());
            statement.setInt(6, shopLine.getQuantity());
            statement.setFloat(7, shopLine.getTotalPrize());

            statement.executeUpdate();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/dawa_tienda", "soutullosobralsamuel", "etse");
    }
}