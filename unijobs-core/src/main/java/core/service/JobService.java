package core.service;

import core.model.Job;
import core.model.UniUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 11/1/2017.
 */
public interface JobService {
    Page<Job> getAll(Pageable pageable);
    Job getOne(int id);
    void save(Job job);

    void clear();

    List<Job> getByTitle(String title);
    Page<Job> getByDescription(String description, Pageable pageable);
    Page<Job> getByLocation(String location, Pageable pageable);
    Page<Job> getByWorkingHours(int hpw, Pageable pageable);
    Page<Job> getByCost(int cost, Pageable pageable);
    Page<Job> getAllByStartDate(Date startDate, Pageable pageable);
    Page<Job> getAllByEndDate(Date endDate, Pageable pageable);
    Page<Job> getAllBetweenDates(Date startDate, Date endDate, Pageable pageable);
    Page<Job> getAllJobsByUser(UniUser uniUser, Pageable pageable);
    List<Job> getAllBySkillDescriptions(List<String> skillDescriptions);
    List<Job> getAllByUserId(Integer userId);

}
