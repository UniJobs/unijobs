package core.service;

import core.model.Job;
import core.model.Request;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface RequestService {
    List<Request> getAll();
    List<Request> getAllForUser(UniUser uniUser);
    List<Request> getAllForJob(Job job);
    Request getOne(int id);
    Request acceptRequest(Integer requestId);
    Request acceptRequest(Integer id,Job job);
    Request rejectRequest(Integer id);
    void insert(Request request);
    void clear();
}
