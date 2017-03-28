package controller;

import model.CDProduct;
import model.ShopCart;
import model.ShopLine;
import model.User;
import model.dao.OrdersDao;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopHelper {
    private HttpSession session;

    public ShopHelper(HttpSession session) {
        this.session = session;

        if(session.getAttribute("shopCart") == null) {
            session.setAttribute("shopCart", new ShopCart());
        }
    }

    public void addToCart(String productString, Integer quantity) {
        CDProduct cdProduct = this.parseProductString(productString);
        ShopCart shopCart = (ShopCart) this.session.getAttribute("shopCart");

        shopCart.add(new ShopLine(cdProduct, quantity));
    }

    public void removeFromCart(Integer index) {
        ShopCart shopCart = (ShopCart) this.session.getAttribute("shopCart");
        shopCart.remove(index);
    }

    public void finishShopCart(String userName, String userEmail) {
        User user = new User();
        user.setName(userName);
        user.setEmail(userEmail);

        this.session.setAttribute("user", user);
    }

    public void finishShopping() {
        ShopCart shopCart = (ShopCart) this.session.getAttribute("shopCart");
        User user = (User) this.session.getAttribute("user");

        OrdersDao.getInstance().insertOrder(shopCart, user);

        shopCart.clear();
    }

    private CDProduct parseProductString(String productString) {
        Pattern regexPattern = Pattern.compile("(.*) \\| (.*) \\| (.*) \\| \\$(.*)");
        Matcher matcher = regexPattern.matcher(productString);
        matcher.find();

        CDProduct cdProduct = new CDProduct();

        cdProduct.setTitle(matcher.group(1));
        cdProduct.setArtist(matcher.group(2));
        cdProduct.setCountry(matcher.group(3));
        cdProduct.setPrize(Float.valueOf(matcher.group(4)));

        return cdProduct;
    }
}