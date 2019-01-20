package core.service;

import com.sun.org.apache.regexp.internal.RE;
import core.model.Job;
import core.model.Request;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Ionut on 10/31/2018.
 */
public interface RequestService {
    List<Request> getAll();
    List<Request> getAllForUser(UniUser uniUser);
    List<Request> getAllForUserByStatus(UniUser uniUser,String status);

    List<Request> getAllForFromUserByStatus(UniUser uniUser, String status);

    List<Request> getAllForJob(Job job);
    List<Request> getAllForFromUser(UniUser uniUser);
    Request getOne(int id);
    Request acceptRequest(Integer requestId);
    Request acceptRequest(Integer id,Job job);
    Request rejectRequest(Integer id);
    Request finishRequest(Integer id);
    void insert(Request request);
    void clear();
}
