package model.dao;

import model.vo.Product;
import model.vo.Rating;

import java.util.List;

public interface RatingDAO {

    boolean addRating(Rating rating);

    List<Rating> listRatings(Product product);

    float calculateAverageRating(Product product);
}