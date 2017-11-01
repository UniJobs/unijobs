package core.service;

import core.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 11/1/2017.
 */
public interface JobService {
    List<Job> getAll();
    Job getOne(int id);
    void insert(Job job);

    List<Job> getByDescription(String description);
    List<Job> getByLocation(String location);
    List<Job> getByWorkingHours(int hpw);
    List<Job> getByCost(int cost);
    List<Job> getAllByStartDate(Date startDate);
    List<Job> getAllByEndDate(Date endDate);
    List<Job> getAllWhereStartDateGreaterOrEqualThanAndEndDateLessOrEqualThan(Date startDate, Date endDate);


}
