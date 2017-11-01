package core.repository;

import core.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Cris on 10/24/2017.
 */
public interface UniJobRepository extends BaseRepository<Job,Integer>{

    @Query("SELECT j FROM Job j WHERE j.description = :description")
    List<Job> getAllByDescription(@Param("description") String description);

    @Query("SELECT j FROM Job j WHERE j.location = :location")
    List<Job> getAllByLocation(@Param("location") String location);

    @Query("SELECT j FROM Job j WHERE j.hoursPerWeek = :hpw")
    List<Job> getAllByHoursPerWeek(@Param("hpw") Integer hpw);

    @Query("SELECT j FROM Job j WHERE j.cost = :cost")
    List<Job> getAllByCost(@Param("cost") Integer cost);

}
