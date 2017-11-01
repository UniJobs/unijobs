package core.service;

import core.model.Job;
import core.repository.UniJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Alex on 11/1/2017.
 */
@Service
public class JobServiceImpl implements JobService {

    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private UniJobRepository jobRepository;

    @Override
    public List<Job> getAll() {
        log.trace("job service - get all");
        List<Job> res = jobRepository.findAll();
        log.trace("job service - got them all");
        return res;
    }

    @Override
    public Job getOne(int id) {
        log.trace("job service - get one {}", id);
        Job res = jobRepository.findOne(id);
        log.trace("job service - got him {}", res);
        return res;
    }

    @Override
    public void insert(Job job) {
        log.trace("job service - inserting {}", job);
        jobRepository.save(job);
        log.trace("job service - done inserting");
    }

    @Override
    @Transactional
    public List<Job> getByDescription(String description) {
        log.trace("job service - get all by description {}", description);
        List<Job> res = jobRepository.getAllByDescription(description);
        log.trace("job service - got them all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getByLocation(String location) {
        log.trace("job service - get all by location {}", location);
        List<Job> res = jobRepository.getAllByLocation(location);
        log.trace("job service - got them all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getByWorkingHours(int hpw) {
        log.trace("job service - get all by hours per week {}", hpw);
        List<Job> res = jobRepository.getAllByHoursPerWeek(hpw);
        log.trace("job service - got them all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getByCost(int cost) {
        log.trace("job service - get all by cost {}", cost);
        List<Job> res = jobRepository.getAllByCost(cost);
        log.trace("job service - got them all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getAllByStartDate(Date startDate) {
        log.trace("job service - get all by start date {}", startDate);
        List<Job> res = jobRepository.getAllByStartDate(startDate);
        log.trace("job service - got them all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getAllByEndDate(Date endDate) {
        log.trace("job service - get all by end date {}", endDate);
        List<Job> res = jobRepository.getAllByEndDate(endDate);
        log.trace("job service - got them all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getAllWhereStartDateGreaterOrEqualThanAndEndDateLessOrEqualThan(Date startDate, Date endDate) {
        log.trace("job service - get all where start date greater or equal than and end date less or equal than {}", startDate, endDate);
        List<Job> res = jobRepository.getAllWhereStartDateGreaterOrEqualThanAndEndDateLessOrEqualThan(startDate, endDate);
        log.trace("job service - got them all");
        return res;
    }


}
