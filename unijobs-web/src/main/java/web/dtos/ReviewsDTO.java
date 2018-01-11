package web.dtos;

import core.model.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import web.dto.ReviewDTO;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ASUS on 08.01.2018.
 */

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ReviewsDTO {
    private List<ReviewDTO> reviews;

    public ReviewsDTO(List<Review> reviews){
        this.reviews = reviews.stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    public void addReview(Review review){reviews.add(new ReviewDTO(review));}
}
