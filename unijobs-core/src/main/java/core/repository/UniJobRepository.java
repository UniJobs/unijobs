package core.repository;

import core.model.Job;
import core.model.Skill;
import core.model.UniUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Cris on 10/24/2017.
 */
public interface UniJobRepository extends BaseRepository<Job,Integer>{

    @Query("SELECT j FROM Job j WHERE j.description  LIKE CONCAT('%',:description,'%') and j.employed is null")
    Page<Job> getAllByDescription(@Param("description") String description, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.location LIKE CONCAT('%',:location,'%') and j.employed is null")
    Page<Job> getAllByLocation(@Param("location") String location, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.hoursPerWeek = :hpw and j.employed is null")
    Page<Job> getAllByHoursPerWeek(@Param("hpw") Integer hpw, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.cost = :cost and j.employed is null")
    Page<Job> getAllByCost(@Param("cost") Integer cost, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.startDate = :startDate and j.employed is null")
    Page<Job> getAllByStartDate(@Param("startDate") Date startDate, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.endDate = :endDate and j.employed is null")
    Page<Job> getAllByEndDate(@Param("endDate") Date endDate, Pageable pageable);

    @Query("SELECT j FROM Job j WHERE j.startDate >= :startDate AND j.endDate <= :endDate and j.employed is null")
    Page<Job> getAllBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

    @Query(value = "select distinct * from job join job_skill on job.id = job_skill.job_id join skill on job_skill.skill_id = skill.id where skill.description in ?1 and job.employed is null",
    nativeQuery = true)
    List<Job> getAllBySkillDescription(List<String> skillDescriptions);

    @Query(value = "select distinct * from job inner join job_skill on job.id = job_skill.job_id where job_skill.skill_id in (select user_skill.skill_id from user_skill where user_skill.user_id = :uid) and job.employed is null",
    nativeQuery = true)
            List<Job> getAllByUserId(@Param("uid") Integer integer);

    //@Query("SELECT j FROM Job j INNER JOIN UniUser u ON j.uniUser = u.id  WHERE u.id = :user_id")
    //List<Job> getAllJobsByUser(@Param("user_id") Integer user_id);

    Page<Job> getAllByUniUser(UniUser uniUser, Pageable pageable);
}
