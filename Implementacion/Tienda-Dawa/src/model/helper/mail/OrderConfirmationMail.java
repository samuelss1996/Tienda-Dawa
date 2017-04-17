package model.helper.mail;

import model.vo.Order;

/**
 * 
 */
public class OrderConfirmationMail extends Mail {

    /**
     * @param order
     */
    public OrderConfirmationMail(Order order) {
        super(String.format("Confirmación de pedido del Sr./Sra. %s", order.getClient().getUsername()),
                String.format("Su pedido se ha recibido correctamente. El precio total es de %.2f", order.getFinalPrice()));
    }
}