package web.controller;

import core.model.Job;
import core.model.Skill;
import core.model.UniUser;
import core.service.JobService;
import core.service.RequestService;
import core.service.SkillService;
import core.service.UniUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.dto.JobDTO;
import web.dtos.JobsDTO;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Cris on 10/24/2017.
 */
@RestController
@RequestMapping("/api/job/")
public class JobController {
    private static final Logger log = LoggerFactory.getLogger(JobController.class);
    private static final Integer PAGE_SIZE = 10;

    @Autowired
    JobService jobService;

    @Autowired
    UniUserService uniUserService;

    @Autowired
    SkillService skillService;

    @Autowired
    RequestService requestService;

    @RequestMapping(value = "jobs/{userId}/{pageNo}", method = RequestMethod.GET)
    @Transactional
    public JobsDTO getJobs(@PathVariable Integer userId,@PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        //return new JobsDTO(jobService.getAll(pageable).getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
        List<Job> jobs = jobService.getAll(pageable).getContent();
        UniUser user = uniUserService.getUserById(userId);
        List<Job> jobList = jobs.stream().filter(job -> job.getUniUser() != user).collect(Collectors.toList());
        return new JobsDTO(jobList.stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    //All jobs unfiltered
    @RequestMapping(value = "allJobs/{pageNo}", method = RequestMethod.GET)
    @Transactional
    public JobsDTO getJobsUnfiltered(@PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        List<Job> jobs = jobService.getAll(pageable).getContent();
        return new JobsDTO(jobs.stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "/newJob", method = RequestMethod.POST)
    public JobDTO addJob(
            @RequestBody final JobDTO jobDTO){

        log.trace("addJob: jobDto={}y", jobDTO);
        Job job;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatterDB = new SimpleDateFormat("d-MMM-yyyy");
        try {
            Date startDate = formatter.parse(jobDTO.getStartDate());
            String dbDate = formatterDB.format(startDate);
            Date endDate = formatter.parse(jobDTO.getEndDate());
            String dbEndDate = formatterDB.format(endDate);
            UniUser user = uniUserService.getUserById(jobDTO.getUniUserId());
            job = Job.builder()
                    .description(jobDTO.getDescription())
                    .location(jobDTO.getLocation())
                    .hoursPerWeek(jobDTO.getHpw())
                    .cost(jobDTO.getCost())
                    .startDate(formatterDB.parse(dbDate))
                    .endDate(formatterDB.parse(dbEndDate))
                    .uniUser(user)
                    .skills(new ArrayList<>())
                    .build();
            jobService.save(job);

            List<Skill> skillz = jobDTO.getSkillIds().stream().map(i -> skillService.getSkillById(i)).collect(Collectors.toList());
            for (Skill s : skillz) {
                log.trace("skill={}", s);
                job.addSkill(s);
            }
            jobService.save(job);
        } catch (DataIntegrityViolationException | ParseException e) {
            job = Job.builder().build();
            log.trace("not added job e={}", e.getMessage(), e.getClass());
        }
        return new JobDTO(job);
    }

    @RequestMapping(value = "byDescription/{description}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByDescription(@PathVariable String description, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        return new JobsDTO(jobService.getByDescription(description, pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byLocation/{location}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByLocation(@PathVariable String location, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, 5);
        Page<Job> jobs = jobService.getByLocation(location, pageable);
        List<JobDTO> dtos = jobs.getContent().stream().map(JobDTO::new).collect(Collectors.toList());
        return new JobsDTO(dtos);
    }

    @RequestMapping(value = "byHPW/{hpw}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByHoursPerWeek(@PathVariable Integer hpw, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        return new JobsDTO(jobService.getByWorkingHours(hpw,pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byCost/{cost}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByCost(@PathVariable Integer cost, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        return new JobsDTO(jobService.getByCost(cost, pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byStartDate/{startDate}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByStartDate(@PathVariable Date startDate, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        return new JobsDTO(jobService.getAllByStartDate(startDate, pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byEndDate/{endDate}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByEndDate(@PathVariable Date endDate, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        return new JobsDTO(jobService.getAllByEndDate(endDate, pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    //Is this working ? solution betweenDates/{startDate}/{endDate}
    @RequestMapping(value = "betweenDates/{startDate, endDate}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsBetweenDates(@PathVariable Date startDate, @PathVariable Date endDate, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        return new JobsDTO(jobService.getAllBetweenDates(startDate, endDate, pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byUser/{user_id}/{pageNo}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getAllJobsByUser(@PathVariable int user_id, @PathVariable Integer pageNo){
        Pageable pageable = new PageRequest(pageNo, PAGE_SIZE);
        UniUser uniUser = uniUserService.getUserById(user_id);
        return new JobsDTO(jobService.getAllJobsByUser(uniUser, pageable)
                .getContent().stream().map(JobDTO::new).collect(Collectors.toList()));
    }

//    @RequestMapping(value = "getAllJobsForUser/{userId}/{pageNo}", method = RequestMethod.GET)
//    @Transactional
//    public JobsDTO getJobsForUser(@PathVariable Integer userId){
//        UniUser u = uniUserService.getUserById(userId);
//        Set<Skill> userSkills = new HashSet<>(u.getSkills());
//        List<Job> allJobs = jobService.getAll();
//        List<JobDTO> result = new ArrayList<>();
//        for (Job j: allJobs){
//            for (Skill s: j.getSkills())
//                if (userSkills.contains(s)) {
//                    result.add(new JobDTO(j));
//                    break;
//                }
//
//        }
//        return new JobsDTO(result);
//    }

    /*
        This is made with native queries so it is better performance-wise
        Unfortunately it does not support pagination

        We can make a workaround which would be better than the query above tho
     */
    @RequestMapping(value = "getAllJobsForUser/{userId}/{pageNo}", method = RequestMethod.GET)
    @Transactional
    public JobsDTO testGetJobsForUser(@PathVariable Integer userId, @PathVariable Integer pageNo){
        List<Job> allJobs = jobService.getAllByUserId(userId);
        if ((pageNo - 1) * PAGE_SIZE > allJobs.size())
            return new JobsDTO();
        int upperSize = pageNo * PAGE_SIZE;
        if (upperSize > allJobs.size())
            upperSize = allJobs.size();
        List<Job> pageWorkaround = allJobs.subList((pageNo-1) * PAGE_SIZE, upperSize);
        return new JobsDTO(pageWorkaround.stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }
}
