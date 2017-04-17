package model.dao;

import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

public interface ProductDAO {

    void insert(CD cd);

    void insert(Cactus cactus);

    void update(CD cd);

    void update(Cactus cactus);

    Product fetchProduct(int productId, EProductType productType);
}