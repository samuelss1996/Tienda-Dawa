package model.dao.sql;

import model.dao.DAOFactory;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.exception.OutOfStockException;
import model.helper.discount.DiscountManager;
import model.helper.mail.Mail;
import model.helper.mail.MailAgent;
import model.helper.mail.OrderConfirmationMail;
import model.vo.*;

import java.sql.*;

/**
 * 
 */
public class SQLOrderDAO implements OrderDAO {

    /**
     * @param client 
     * @param shopCart 
     * @return
     */
    public Order createOrder(Client client, ShopCart shopCart) throws OutOfStockException {
        Order order = new Order();
        int lineNumber = 0;

        order.setClient(client);
        order.setDiscount(DiscountManager.getDiscount(client));

        for (OrderLine orderLine : shopCart.getLines()) {
            int quantity = orderLine.getQuantity();
            ProductDAO productDAO = DAOFactory.getFactory(DAOFactory.SQL).getProductDAO();
            Product product = productDAO.fetchProduct(orderLine.getProduct().getId(), orderLine.getProduct().getType());
            if (quantity > product.getStock())
                throw new OutOfStockException();

            order.addLine(new OrderLine(lineNumber, product, quantity, product.getPrice()));
            lineNumber ++;
        }
        return order;
    }

    /**
     * @param order
     */
    public void confirmOrder(Order order) throws OutOfStockException {
        try (Connection connection = SQLDAOFactory.createConnection()) {
            connection.setAutoCommit(false);
            for (OrderLine line : order.getLines()) {
                if (outOfSTock(line.getProduct(), line.getQuantity(), connection)) {
                    connection.rollback();
                    throw new OutOfStockException();
                }
            }
            insertOrder(order, connection);
            upgradeClient(order.getClient(), order.getFinalPrice(), connection);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sendConfirmationEmail(order);
    }

    private void upgradeClient(Client client, float newExpense, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT totalExpenses FROM client WHERE id = ?")) {
            preparedStatement.setInt(1, client.getId());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                float totalExpenses = resultSet.getFloat(1);
                float updatedExpenses = totalExpenses + newExpense;
                String update = "";
                if (updatedExpenses > 100) {
                    update = "SET totalExpenses = " + updatedExpenses + ", type = " + 2;
                } else {
                    update = "SET totalExpenses = " + updatedExpenses;
                }
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate("UPDATE client " + update + " WHERE id = " + client.getId());
                }
            }
        }
    }

    private void sendConfirmationEmail(Order order) {
        Mail mail = new OrderConfirmationMail(order);
        new MailAgent().sendMail(order.getClient(), mail);
    }

    private void insertOrder(Order order, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into `order` (finalPrice, discount, client) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setFloat(1, order.getFinalPrice());
            preparedStatement.setFloat(2, order.getDiscount());
            preparedStatement.setInt(3, order.getClient().getId());

            preparedStatement.executeUpdate();

            ResultSet generatedKeySet = preparedStatement.getGeneratedKeys();
            if (generatedKeySet.next()) {
                int lineNumber = 0;
                for (OrderLine line : order.getLines()) {
                    line.setLineNumber(lineNumber); lineNumber++;
                    insertLine(line, generatedKeySet.getInt(1), connection);
                }
            } else {
                throw new SQLException();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    private void insertLine(OrderLine line, int orderId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orderline (`order`, lineNumber, product, quantity, unitPrice) VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, line.getLineNumber());
            preparedStatement.setInt(3, line.getProduct().getId());
            preparedStatement.setInt(4, line.getQuantity());
            preparedStatement.setFloat(5, line.getUnitPrice());

            preparedStatement.executeUpdate();
        }
    }

    private boolean outOfSTock(Product product, int quantity, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE id = ? AND stock < ?")) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setInt(2, product.getStock()); // TODO aquí se te fue la olla?

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.first();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
            return true;
        }
    }

}