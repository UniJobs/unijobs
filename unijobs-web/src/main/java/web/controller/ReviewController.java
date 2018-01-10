package web.controller;

import core.model.Review;
import core.service.ReviewService;
import core.service.UniUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dto.ReviewDTO;
import web.dtos.ReviewsDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ASUS on 08.01.2018.
 */

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private static final Logger log = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    ReviewService reviewService;

    @Autowired
    UniUserService uniUserService;

    @RequestMapping(value = "reviews", method = RequestMethod.GET)
    @Transactional
    public ReviewsDTO getReviewsForUserId(@RequestBody Integer userId){
        List<Review> reviews = uniUserService.getReviewsForUserId(userId);
        return new ReviewsDTO(reviews.stream().map(ReviewDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/newReviewForUser", method = RequestMethod.POST)
    public ReviewDTO addReviewForUser(
            @RequestBody final ReviewDTO reviewDTO){

        log.trace("addReviewForUser: reviewDto={}", reviewDTO);
        Review review;
        review = reviewService.getOne(reviewDTO.getId());
        try {
            review = Review.builder()
                    .reviewer(reviewDTO.getReviewer())
                    .reviewed(reviewDTO.getReviewed())
                    .stars(reviewDTO.getStars())
                    .comment(reviewDTO.getComment())
                    .build();
            reviewService.addReviewForUser(review);
        } catch (DataIntegrityViolationException e) {
            review = Review.builder().build();
            log.trace("not added review={}", review);
        }
        return new ReviewDTO(review);
    }
}
