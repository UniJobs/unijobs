package web.controller;

import core.model.Job;
import core.service.FetchService;
import core.service.JobService;
import core.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dto.JobDTO;
import web.dtos.JobsDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cris on 10/24/2017.
 */
@RestController
@RequestMapping("/api/job")
public class JobController {

    @Autowired
    JobService jobService;

    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    @Transactional
    public JobsDTO getJobs(){
        return new JobsDTO(jobService.getAll().stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byDescription/{description}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByDescription(@PathVariable String description){
        return new JobsDTO(jobService.getByDescription(description).stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byLocation/{location}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByLocation(@PathVariable String location){
        List<Job> jobs = jobService.getByLocation(location);
        List<JobDTO> dtos = jobs.stream().map(j -> new JobDTO(j)).collect(Collectors.toList());
        return new JobsDTO(dtos);
    }

    @RequestMapping(value = "byHPW/{hpw}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByHoursPerWeek(@PathVariable Integer hpw){
        return new JobsDTO(jobService.getByWorkingHours(hpw).stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byCost/{cost}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByCost(@PathVariable Integer cost){
        return new JobsDTO(jobService.getByCost(cost).stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byStartDate/{startDate}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByStartDate(@PathVariable Date startDate){
        return new JobsDTO(jobService.getAllByStartDate(startDate).stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "byEndDate/{endDate}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsByEndDate(@PathVariable Date endDate){
        return new JobsDTO(jobService.getAllByEndDate(endDate).stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }

    @RequestMapping(value = "betweenDates/{startDate, endDate}", method = RequestMethod.POST)
    @Transactional
    public JobsDTO getJobsBetweenDates(@PathVariable Date startDate, @PathVariable Date endDate){
        return new JobsDTO(jobService.getAllWhereStartDateGreaterOrEqualThanAndEndDateLessOrEqualThan(startDate, endDate).stream().map(j -> new JobDTO(j)).collect(Collectors.toList()));
    }
}
