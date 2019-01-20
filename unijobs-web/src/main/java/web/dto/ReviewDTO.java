package web.dto;

import core.model.Review;
import core.model.UniUser;
import lombok.*;

/**
 * Created by Eric on 08.01.2018.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReviewDTO {
    private int id;
    private Integer reviewerId;
    private Integer reviewedId;
    private int stars;
    private String comment;

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.reviewerId = review.getReviewer().getId();
        this.reviewedId = review.getReviewed().getId();
        this.stars = review.getStars();
        this.comment = review.getComment();
    }
}
