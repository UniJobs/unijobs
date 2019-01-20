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
 * Created by Madalina on 10/24/2018.
 */
public interface UniJobRepository extends BaseRepository<Job,Integer>{

    String standardQuery =
            "select distinct j.* from job j inner join job_skill on j.id = job_skill.job_id where" +
            " job_skill.skill_id in (select user_skill.skill_id from user_skill where user_skill.user_id = :uid) and" +
            " j.employed_id is null and j.user_id != :uid";
    String paginatedQuery = " limit :page_size offset :page_offset";


    @Query(value = standardQuery + " and j.description  LIKE CONCAT('%',:description,'%')" + paginatedQuery,
            nativeQuery = true)
    List<Job> getAllByDescription(@Param("uid") Integer uid,
                                  @Param("page_size") Integer page_size, @Param("page_offset") Integer page_offset,
                                  @Param("description") String description);

    @Query(value = standardQuery + " and j.location LIKE CONCAT('%',:location,'%')" + paginatedQuery,
            nativeQuery = true)
    List<Job> getAllByLocation(@Param("uid") Integer uid,
                               @Param("page_size") Integer page_size, @Param("page_offset") Integer page_offset,
                               @Param("location") String location);

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

    @Query(value = "select distinct j.* from job j join job_skill on j.id = job_skill.job_id " +
            "join skill on job_skill.skill_id = skill.id where skill.description in ?1 and j.employed is null",
            nativeQuery = true)
    List<Job> getAllBySkillDescription(List<String> skillDescriptions);

    @Query(value = standardQuery + paginatedQuery,
            nativeQuery = true)
    List<Job> getAllByUserId(@Param("uid") Integer integer, @Param("page_size") Integer page_size,
                             @Param("page_offset") Integer page_offset);

    //@Query("SELECT j FROM Job j INNER JOIN UniUser u ON j.uniUser = u.id  WHERE u.id = :user_id")
    //List<Job> getAllJobsByUser(@Param("user_id") Integer user_id);

    Page<Job> getAllByUniUser(UniUser uniUser, Pageable pageable);
}
