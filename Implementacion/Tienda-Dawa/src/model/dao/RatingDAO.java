package model.dao;

import model.vo.Product;
import model.vo.Rating;

/**
 * 
 */
public interface RatingDAO {

    /**
     * @param rating
     */
    void addRating(Rating rating);

    /**
     * @param product
     */
    void listRatings(Product product);
}