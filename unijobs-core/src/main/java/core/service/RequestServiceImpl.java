package core.service;

import core.model.Request;
import core.model.UniUser;
import core.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 10/31/2017.
 */
@Service
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestService.class);

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> getAll() {
        log.trace("Get all providers");
        List<Request> res = requestRepository.findAll();
        log.trace("Got all providers : providers ={}",res);
        return res;
    }

    @Override
    public List<Request> getAllForUser(UniUser uniUser) {
        return requestRepository.findAllByToUniUser(uniUser).stream()
                .filter(r -> r.getStatus().equals("PENDING")).collect(Collectors.toList());
    }

    @Override
    public Request getOne(int id) {
        log.trace("Get request by id : id={}", id);
        Request res = requestRepository.findOne(id);
        log.trace("Request = {}", res);
        return res;
    }

    @Override
    public Request acceptRequest(Integer id) {
        Request request = getOne(id);
        request.setStatus("ACCEPTED");
        requestRepository.save(request);
        return request;
    }

    @Override
    public Request rejectRequest(Integer id) {
        Request request = getOne(id);
        request.setStatus("REJECTED");
        requestRepository.save(request);
        return request;
    }

    @Override
    public void insert(Request request) {
        log.trace("Inserting request : request = {}",request);
        requestRepository.save(request);
        log.trace("Request inserted");
    }

    @Override
    public void clear() {
        log.trace("clear requests");
        requestRepository.deleteAll();
        log.trace("Requests cleared");
    }
}
