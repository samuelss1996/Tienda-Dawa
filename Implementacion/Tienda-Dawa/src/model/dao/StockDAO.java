package model.dao;

import model.filter.CDFilter;
import model.filter.CactusFilter;
import model.filter.ProductFilter;
import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

import java.util.*;

public interface StockDAO {

    List<Product> listAvailableProducts(int limit);

    List<Product> listAvailableProducts(EProductType type, int limit);

    List<Product> searchProducts(ProductFilter filter);

    List<CD> searchCDs(CDFilter filter);

    List<Cactus> searchCacti(CactusFilter filter);

    boolean isOwner(String username, int productId);
}