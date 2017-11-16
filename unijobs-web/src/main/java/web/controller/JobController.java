package web.controller;

import core.model.Job;
import core.model.Skill;
import core.model.UniUser;
import core.service.JobService;
import core.service.SkillService;
import core.service.UniUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Autowired
    JobService jobService;

    @Autowired
    UniUserService uniUserService;

    @Autowired
    SkillService skillService;

    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    @Transactional
    public JobsDTO getJobs(){
        return new JobsDTO(jobService.getAll().stream().map(JobDTO::new).collect(Collectors.toList()));
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

    @RequestMapping(value = "byDescription/{description}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByDescription(@PathVariable String description){
        return new JobsDTO(jobService.getByDescription(description).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byLocation/{location}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByLocation(@PathVariable String location){
        List<Job> jobs = jobService.getByLocation(location);
        List<JobDTO> dtos = jobs.stream().map(JobDTO::new).collect(Collectors.toList());
        return new JobsDTO(dtos);
    }

    @RequestMapping(value = "byHPW/{hpw}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByHoursPerWeek(@PathVariable Integer hpw){
        return new JobsDTO(jobService.getByWorkingHours(hpw).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byCost/{cost}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByCost(@PathVariable Integer cost){
        return new JobsDTO(jobService.getByCost(cost).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byStartDate/{startDate}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByStartDate(@PathVariable Date startDate){
        return new JobsDTO(jobService.getAllByStartDate(startDate).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byEndDate/{endDate}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByEndDate(@PathVariable Date endDate){
        return new JobsDTO(jobService.getAllByEndDate(endDate).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "betweenDates/{startDate, endDate}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsBetweenDates(@PathVariable Date startDate, @PathVariable Date endDate){
        return new JobsDTO(jobService.getAllBetweenDates(startDate, endDate).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byUser/{user_id}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getAllJobsByUser(@PathVariable int user_id){
        UniUser uniUser = uniUserService.getUserById(user_id);
        return new JobsDTO(jobService.getAllJobsByUser(uniUser).stream().map(JobDTO::new).collect(Collectors.toList()));
    }

    @RequestMapping(value = "getAllJobsForUser/{userId}", method = RequestMethod.GET)
    @Transactional
    public JobsDTO getJobsForUser(@PathVariable Integer userId){
        UniUser u = uniUserService.getUserById(userId);
        Set<Skill> userSkills = new HashSet<>(u.getSkills());
        List<Job> allJobs = jobService.getAll();
        List<JobDTO> result = new ArrayList<>();
        for (Job j: allJobs){
            for (Skill s: j.getSkills())
                if (userSkills.contains(s)) {
                    result.add(new JobDTO(j));
                    break;
                }

        }
        return new JobsDTO(result);
    }
}
