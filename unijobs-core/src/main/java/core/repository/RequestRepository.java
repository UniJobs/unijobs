package core.repository;

import core.model.Job;
import core.model.Request;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface RequestRepository extends BaseRepository<Request, Integer> {

    List<Request> findAllByToUniUser(UniUser uniUser);
    List<Request> findAllByJob(Job job);
}
