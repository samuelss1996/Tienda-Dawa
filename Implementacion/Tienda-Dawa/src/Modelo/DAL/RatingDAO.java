package Modelo.DAL;

import java.util.*;

/**
 * 
 */
public interface RatingDAO {

    /**
     * @param rating
     */
    public void addRating(Rating rating);

    /**
     * @param product
     */
    public void listRatings(Product product);

}