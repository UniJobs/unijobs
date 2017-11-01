package core.service;

import core.model.Provider;
import core.model.Request;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface RequestService {
    List<Request> getAll();
    List<Request> getAllForProvider(Provider provider);
    Request getOne(int id);
    void insert(Request request);
    void clear();
}
