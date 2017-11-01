package core.service;

import core.model.Provider;
import core.model.Request;
import core.repository.RequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Request> getAllForProvider(Provider provider) {
        return null;
    }

    @Override
    public Request getOne(int id) {
        log.trace("Get request by id : id={}", id);
        Request res = requestRepository.findOne(id);
        log.trace("Request = {}", res);
        return res;
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
