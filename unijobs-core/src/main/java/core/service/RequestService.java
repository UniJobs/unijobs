package core.service;

import core.model.Request;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface RequestService {
    List<Request> getAll();
    List<Request> getAllForUser(UniUser uniUser);
    Request getOne(int id);
    Request acceptRequest(Integer id);
    Request rejectRequest(Integer id);
    void insert(Request request);
    void clear();
}
