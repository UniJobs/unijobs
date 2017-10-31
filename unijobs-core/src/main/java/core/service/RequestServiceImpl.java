package core.service;

import core.model.Provider;
import core.model.Request;
import core.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
@Service
public class RequestServiceImpl implements RequestService {
    //TODO: logger

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public List<Request> getAll() {
        List<Request> res = requestRepository.findAll();
        return res;
    }

    @Override
    public List<Request> getAllForProvider(Provider provider) {
        return null;
    }

    @Override
    public Request getOne(int id) {
        Request res = requestRepository.findOne(id);
        return res;
    }

    @Override
    public void insert(Request request) {
        requestRepository.save(request);
    }
}
