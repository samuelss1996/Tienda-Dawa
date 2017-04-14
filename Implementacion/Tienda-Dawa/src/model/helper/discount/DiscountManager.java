package model.helper.discount;

import model.vo.Client;

/**
 * Created by crist on 14/04/2017.
 */
public class DiscountManager {
    public static Float getDiscount(Client client) {
        switch (client.getType()) {
            case STANDARD:
                return 0f;
            case VIP:
                return 20f;
            default:
                return 0f;
        }
    }
}
