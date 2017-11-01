package core.service;

import core.model.Job;

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

}
