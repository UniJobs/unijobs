package core.service;

import core.model.Review;
import core.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ionut on 10/31/2018.
 */
@Service
public class ReviewServiceImpl implements  ReviewService {

    private static final Logger log = LoggerFactory.getLogger(ReviewService.class);

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAll() {
        log.trace("get all reviews");
        List<Review> res = reviewRepository.findAll();
        log.trace("Got all reviews: reviews={}",res);
        return res;
    }

    @Override
    public Review getOne(int id) {
        log.trace("Get review by id : id={}",id);
        Review res = reviewRepository.findOne(id);
        log.trace("Got review : review={}",res);
        return res;
    }

    @Override
    public void insert(Review review) {
        log.trace("add review : review={}",review);
        reviewRepository.save(review);
        log.trace("review added");
    }

    @Override
    public void clear() {
        log.trace("clear Reviews");
        reviewRepository.deleteAll();
        log.trace("Reviews cleared");
    }

    @Override
    public void addReviewForUser(Review review){
        log.trace("add review for a user", review);
        reviewRepository.save(review);
        log.trace("add review for a user");
    }

    @Override
    public List<Review> getReviewsForUserId(Integer userId){
        log.trace("Get reviews by user id : id={}",userId);
        List<Review> reviews = reviewRepository.getReviewsForUserId(userId);
        log.trace("Reviews returned for userId={} are reviews={}", userId, reviews);
        return reviews;
    }
}
