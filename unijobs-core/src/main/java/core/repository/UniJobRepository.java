package core.repository;

import core.model.Job;
import core.model.UniUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Cris on 10/24/2017.
 */
/*Acest comentariu trebuie sters.*/
public interface UniJobRepository extends BaseRepository<Job,Integer>{

    @Query("SELECT j FROM Job j WHERE j.description  LIKE CONCAT('%',:description,'%')")
    List<Job> getAllByDescription(@Param("description") String description);

    @Query("SELECT j FROM Job j WHERE j.location LIKE CONCAT('%',:location,'%')")
    List<Job> getAllByLocation(@Param("location") String location);

    @Query("SELECT j FROM Job j WHERE j.hoursPerWeek = :hpw")
    List<Job> getAllByHoursPerWeek(@Param("hpw") Integer hpw);

    @Query("SELECT j FROM Job j WHERE j.cost = :cost")
    List<Job> getAllByCost(@Param("cost") Integer cost);

    @Query("SELECT j FROM Job j WHERE j.startDate = :startDate")
    List<Job> getAllByStartDate(@Param("startDate") Date startDate);

    @Query("SELECT j FROM Job j WHERE j.endDate = :endDate")
    List<Job> getAllByEndDate(@Param("endDate") Date endDate);

    @Query("SELECT j FROM Job j WHERE j.startDate >= :startDate AND j.endDate <= :endDate")
    List<Job> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    //@Query("SELECT j FROM Job j INNER JOIN UniUser u ON j.uniUser = u.id  WHERE u.id = :user_id")
    //List<Job> getAllJobsByUser(@Param("user_id") Integer user_id);

    List<Job> getAllByUniUser(UniUser uniUser);

}
