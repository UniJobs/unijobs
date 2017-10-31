package core.service;

import core.model.Review;
import core.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
@Service
public class ReviewServiceImpl implements  ReviewService {

    //TODO: logger

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAll() {
        List<Review> res = reviewRepository.findAll();
        return res;
    }

    @Override
    public Review getOne(int id) {
        Review res = reviewRepository.findOne(id);
        return res;
    }

    @Override
    public void insert(Review review) {
        reviewRepository.save(review);
    }
}
