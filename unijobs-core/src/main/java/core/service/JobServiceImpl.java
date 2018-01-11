package core.service;

import core.model.Job;
import core.model.UniUser;
import core.repository.UniJobRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 11/1/2017.
 */
@Service
public class JobServiceImpl implements JobService {

    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private UniJobRepository jobRepository;

    @Override
    public Page<Job> getAll(Pageable pageable) {
        log.trace("job service - get all");
        Page<Job> res = jobRepository.findAll(pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    public Job getOne(int id) {
        log.trace("job service - get one {}", id);
        Job res = jobRepository.findOne(id);
        log.trace("job service - got him {}", res);
        return res;
    }

    @Override
    public void save(Job job) {
        log.trace("job service - inserting {}", job);
        jobRepository.save(job);
        log.trace("job service - done inserting");
    }

    @Override
    public void clear() {
        log.trace("job service - remove all");
        jobRepository.deleteAll();
        log.trace("job service - done removing");
    }

    @Override
    @Transactional
    public Page<Job> getByDescription(String description, Pageable pageable) {
        log.trace("job service - get all by description {}", description);
        Page<Job> res = jobRepository.getAllByDescription(description, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getByLocation(String location, Pageable pageable) {
        log.trace("job service - get all by location {}", location);
        Page<Job> res = jobRepository.getAllByLocation(location, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getByWorkingHours(int hpw, Pageable pageable) {
        log.trace("job service - get all by hours per week {}", hpw);
        Page<Job> res = jobRepository.getAllByHoursPerWeek(hpw, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getByCost(int cost, Pageable pageable) {
        log.trace("job service - get all by cost {}", cost);
        Page<Job> res = jobRepository.getAllByCost(cost, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getAllByStartDate(Date startDate, Pageable pageable) {
        log.trace("job service - get all by start date {}", startDate);
        Page<Job> res = jobRepository.getAllByStartDate(startDate, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getAllByEndDate(Date endDate, Pageable pageable) {
        log.trace("job service - get all by end date {}", endDate);
        Page<Job> res = jobRepository.getAllByEndDate(endDate, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getAllBetweenDates(Date startDate, Date endDate, Pageable pageable) {
        log.trace("job service - get all where start date greater or equal than and end date less or equal than {}", startDate, endDate);
        Page<Job> res = jobRepository.getAllBetweenDates(startDate, endDate, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    @Override
    @Transactional
    public Page<Job> getAllJobsByUser(UniUser uniUser, Pageable pageable) {
        log.trace("job service - get all jobs published by a user {}", uniUser);
        Page<Job> res = jobRepository.getAllByUniUser(uniUser, pageable);
        log.trace("job service - got them all");
        return filterAvailableJobs(res);
    }

    public List<Job> filterAvailableJobs(List<Job> jobs){
        return jobs.stream().filter(job -> job.isAvailable() && !job.getEndDate().after(new Date())).collect(Collectors.toList());
    }

    @Override
    public List<Job> getAllBySkillDescriptions(List<String> skillDescriptions) {
        List<Job> res = jobRepository.getAllBySkillDescription(skillDescriptions);
        return res;
    }

    @Override
    public List<Job> getAllByUserId(Integer userId) {
        List<Job> res = jobRepository.getAllByUserId(userId);
        return res;
    }


}
