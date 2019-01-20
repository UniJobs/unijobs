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
 * Created by Ionut on 11/1/2018.
 */
@Service
public class JobServiceImpl implements JobService {

    private static final Logger log = LoggerFactory.getLogger(JobService.class);

    @Autowired
    private UniJobRepository jobRepository;

    @Override
    public List<Job> getAllNonPage() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> getAll(Pageable pageable) {
        log.trace("job service - get all");
        Page<Job> res = jobRepository.findAll(pageable);
        log.trace("job service - got them all");
        return res.getContent();
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
    public List<Job> getByDescription(String description, Integer userId, Integer pageSize, Integer pageOffset) {
        log.trace("job service - get all by description for user {}", userId);
        List<Job> res = jobRepository.getAllByDescription(userId, pageSize, pageOffset, description);
        log.trace("job service - done getting all");
        return res;
    }

    @Override
    @Transactional
    public List<Job> getByLocation(String location, Integer userId, Integer pageSize, Integer pageOffset) {
        log.trace("job service - get all by location for user {}", userId);
        List<Job> res = jobRepository.getAllByLocation(userId, pageSize, pageOffset, location);
        log.trace("job service - done getting all");
        return res;
    }


    @Override
    @Transactional
    public List<Job> getByWorkingHours(int hpw, Pageable pageable) {
        log.trace("job service - get all by hours per week {}", hpw);
        Page<Job> res = jobRepository.getAllByHoursPerWeek(hpw, pageable);
        log.trace("job service - got them all");
        return res.getContent();
    }

    @Override
    @Transactional
    public List<Job> getByCost(int cost, Pageable pageable) {
        log.trace("job service - get all by cost {}", cost);
        Page<Job> res = jobRepository.getAllByCost(cost, pageable);
        log.trace("job service - got them all");
        return res.getContent();
    }

    @Override
    @Transactional
    public List<Job> getAllByStartDate(Date startDate, Pageable pageable) {
        log.trace("job service - get all by start date {}", startDate);
        Page<Job> res = jobRepository.getAllByStartDate(startDate, pageable);
        log.trace("job service - got them all");
        return res.getContent();
    }

    @Override
    @Transactional
    public List<Job> getAllByEndDate(Date endDate, Pageable pageable) {
        log.trace("job service - get all by end date {}", endDate);
        Page<Job> res = jobRepository.getAllByEndDate(endDate, pageable);
        log.trace("job service - got them all");
        return res.getContent();
    }

    @Override
    @Transactional
    public List<Job> getAllBetweenDates(Date startDate, Date endDate, Pageable pageable) {
        log.trace("job service - get all where start date greater or equal than and end date less or equal than {}", startDate, endDate);
        Page<Job> res = jobRepository.getAllBetweenDates(startDate, endDate, pageable);
        log.trace("job service - got them all");
        return res.getContent();
    }

    @Override
    @Transactional
    public List<Job> getAllJobsByUser(UniUser uniUser, Pageable pageable) {
        log.trace("job service - get all jobs published by a user {}", uniUser);
        Page<Job> res = jobRepository.getAllByUniUser(uniUser, pageable);
        log.trace("job service - got them all");
        return res.getContent();
    }

    @Override
    public List<Job> getAllBySkillDescriptions(List<String> skillDescriptions) {
        return jobRepository.getAllBySkillDescription(skillDescriptions);
    }

    @Override
    public List<Job> getAllByUserId(Integer userId, Integer pageSize, Integer pageOffset) {
        return jobRepository.getAllByUserId(userId, pageSize, pageOffset);
    }


}
