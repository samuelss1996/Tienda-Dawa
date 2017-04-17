package model.helper.discount;

import model.vo.Client;

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
