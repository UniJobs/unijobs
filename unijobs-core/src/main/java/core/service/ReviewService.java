package core.service;

import core.model.Review;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface ReviewService {
    List<Review> getAll();
    Review getOne(int id);
    void insert(Review review);
    void clear();
    void addReviewForUser(Review review);
    List<Review> getReviewsForUserId(Integer userId);
}
