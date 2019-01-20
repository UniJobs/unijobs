package core.repository;

import core.model.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Ionut on 10/31/2018.
 */
public interface ReviewRepository extends BaseRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.id = :id")
    Review getReviewById(@Param("id")Integer id);

    @Query("SELECT r FROM Review r INNER JOIN UniUser u ON u.id = r.reviewed.id WHERE r.reviewed.id = :userId")
    List<Review> getReviewsForUserId(@Param("userId") Integer userId);
}
