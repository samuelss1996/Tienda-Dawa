package model.dao;

import model.vo.Product;
import model.vo.Rating;

import java.util.List;

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
    List<Rating> listRatings(Product product);

    /**
     *
     * @param product
     * @return
     */
    float calculateAverageRating(Product product);
}