package web.dtos;

import core.model.Review;
import web.dto.ReviewDTO;

import java.util.List;

/**
 * Created by ASUS on 08.01.2018.
 */
public class ReviewsDTO {
    List<ReviewDTO> reviews;

    public ReviewsDTO(List<ReviewDTO> reviewDTOS){
        this.reviews = reviewDTOS;
    }

    public void addReview(Review review){reviews.add(new ReviewDTO(review));}
}
