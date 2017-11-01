package core.service;

import core.model.Job;
import core.model.Skill;
import core.model.TemporaryUser;
import core.model.UniUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FetchService {
    List<Skill> getAllSkills();
    Skill findSkill(Long id);

    UniUser getOneByUsername(String username);
    UniUser getOneById(Integer id);

    List<Job> getAllJobs();
    Job findJob(int id);

    List<UniUser> getAllUsers();
    List<TemporaryUser> getAllTemporaryUsers();
    TemporaryUser getTemporaryUserById(Long id);
}
