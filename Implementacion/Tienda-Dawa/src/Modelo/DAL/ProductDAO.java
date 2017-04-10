package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public interface ProductDAO {

    /**
     * @param cd
     */
    public void insert(CD cd);

    /**
     * @param cactus
     */
    public void insert(Cactus cactus);

    /**
     * @param productId 
     * @param productType 
     * @return
     */
    public Product fetchProduct(int productId, EProductType productType);

}