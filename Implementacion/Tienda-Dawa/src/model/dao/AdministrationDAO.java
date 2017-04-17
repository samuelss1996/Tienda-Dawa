package model.dao;

import model.filter.ClientFilter;
import model.vo.EProductType;
import model.vo.Product;
import model.vo.User;

import java.util.*;

public interface AdministrationDAO {

    List<User> listUserAccounts(int from, int to, ClientFilter filter);

    void deleteUserAccounts(int userId);

    List<Product> listProducts(int from, int to);

    List<Product> listProducts(int from, int to, EProductType type);

    void changeUserPassword(int userId, String newPassword);
}