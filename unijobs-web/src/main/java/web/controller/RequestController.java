package web.controller;

import core.model.Job;
import core.model.Request;
import core.model.UniUser;
import core.service.JobService;
import core.service.RequestService;
import core.service.UniUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import web.dto.RequestDTO;
import web.dtos.RequestsDTO;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    private static final Logger log = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    JobService jobService;

    @Autowired
    UniUserService uniUserService;

    @Autowired
    RequestService requestService;

    // !!!
    // Request STATUS : PENDING | ACCEPTED | REJECTED

    @RequestMapping(value = "/requests/{userId}", method = RequestMethod.GET)
    public RequestsDTO getRequests(@PathVariable Integer userId){
        log.trace("Finding all request for user : {}",userId);
        List<Request> requests = requestService.getAllForUser(uniUserService.getUserById(userId));
        log.trace("Request for user {} are : {}",userId,requests);
        return new RequestsDTO(requests);
    }

    @RequestMapping(value = "/requestsFrom/{userId}", method = RequestMethod.GET)
    public RequestsDTO getRequestsFrom(@PathVariable Integer userId){
        log.trace("Finding all request for user : {}",userId);
        List<Request> requests = requestService.getAllForFromUser(uniUserService.getUserById(userId));
        log.trace("Request for user {} are : {}",userId,requests);
        return new RequestsDTO(requests);
    }



    @RequestMapping(value = "/requestJob/{userId}/{jobId}",method = RequestMethod.POST)
    public RequestDTO sendRequest(@PathVariable Integer userId, @PathVariable Integer jobId){
        log.trace("Send Request from {} to {}",userId, jobId);
        UniUser toUser = jobService.getOne(jobId).getUniUser();
        UniUser fromUser = uniUserService.getUserById(userId);
        Job job = jobService.getOne(jobId);
        Request request = Request.builder().fromUniUser(fromUser).job(job).toUniUser(toUser).status("PENDING").build();
        requestService.insert(request);
        log.trace("Request sent from {} to {}",userId, jobId);
        return new RequestDTO(request);
    }

    @RequestMapping(value = "/accept/{requestId}",method = RequestMethod.POST)
    public RequestDTO acceptRequest(@PathVariable Integer requestId){
        log.trace("Accepting request : {}",requestId);
        Job job = requestService.getOne(requestId).getJob();
        Request request = requestService.acceptRequest(requestId,job);
        job.setEmployed(uniUserService.getUserById(request.getFromUniUser().getId()));
        jobService.save(job);
        log.trace("Accepted request : {}",requestId);
        return new RequestDTO(request);
    }

    @RequestMapping(value = "/reject/{requestId}",method = RequestMethod.POST)
    public RequestDTO rejectRequest(@PathVariable Integer requestId){
        log.trace("Rejecting request : {}",requestId);
        Request request = requestService.rejectRequest(requestId);
        log.trace("Rejecting request : {}",requestId);
        return new RequestDTO(request);
    }


}
