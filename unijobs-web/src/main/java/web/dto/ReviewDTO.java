package web.dto;

import core.model.Review;
import core.model.UniUser;
import lombok.*;

/**
 * Created by ASUS on 08.01.2018.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ReviewDTO {
    private int id;
    private UniUser reviewer;
    private UniUser reviewed;
    private int stars;
    private String comment;

    public ReviewDTO(Review review){
        this.id = review.getId();
        this.reviewer = review.getReviewer();
        this.reviewed = review.getReviewed();
        this.stars = review.getStars();
        this.comment = review.getComment();
    }
}
