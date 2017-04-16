package model.dao;

import model.vo.CD;
import model.vo.Cactus;
import model.vo.EProductType;
import model.vo.Product;

/**
 * 
 */
public interface ProductDAO {

    /**
     * @param cd
     */
    void insert(CD cd);

    /**
     * @param cactus
     */
    void insert(Cactus cactus);

    /**
     *
     * @param cd
     */
    void update(CD cd);

    /**
     *
     * @param cactus
     */
    void update(Cactus cactus);

    /**
     * @param productId 
     * @param productType 
     * @return
     */
    Product fetchProduct(int productId, EProductType productType);
}