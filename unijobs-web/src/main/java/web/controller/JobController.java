package web.controller;

import core.service.FetchService;
import core.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dtos.JobsDTO;

/**
 * Created by Cris on 10/24/2017.
 */
@RestController
public class JobController {
    @Autowired
    FetchService fetchService;

    @Autowired
    ManageService manageServicea;

    @RequestMapping(value = "jobs", method = RequestMethod.GET)
    public JobsDTO getJobs(){return new JobsDTO(fetchService.getAllJobs());}
}
