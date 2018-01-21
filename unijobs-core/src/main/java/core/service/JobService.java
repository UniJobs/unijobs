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
    List<Job> getAllNonPage();
    List<Job> getAll(Pageable pageable);
    Job getOne(int id);
    void save(Job job);

    void clear();

    List<Job> getByDescription(String description, Integer userId, Integer pageSize, Integer pageOffset);
    List<Job> getByLocation(String location, Integer userId, Integer pageSize, Integer pageOffset);
    List<Job> getByWorkingHours(int hpw, Pageable pageable);
    List<Job> getByCost(int cost, Pageable pageable);
    List<Job> getAllByStartDate(Date startDate, Pageable pageable);
    List<Job> getAllByEndDate(Date endDate, Pageable pageable);
    List<Job> getAllBetweenDates(Date startDate, Date endDate, Pageable pageable);
    List<Job> getAllJobsByUser(UniUser uniUser, Pageable pageable);
    List<Job> getAllBySkillDescriptions(List<String> skillDescriptions);
    List<Job> getAllByUserId(Integer userId, Integer pageSize, Integer pageOffset);

}
