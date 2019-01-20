package core.repository;

import core.model.Job;
import core.model.Request;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Ionut on 10/31/2018.
 */
public interface RequestRepository extends BaseRepository<Request, Integer> {

    List<Request> findAllByToUniUser(UniUser uniUser);
    List<Request> findAllByFromUniUser(UniUser uniUser);
    List<Request> findAllByJob(Job job);
}
