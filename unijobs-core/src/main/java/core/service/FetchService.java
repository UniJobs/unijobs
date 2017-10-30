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

    List<Job> getAllJobs();

    Job findJob(Long id);

    List<UniUser> getAllUsers();

    List<TemporaryUser> getAllTemporaryUsers();

    TemporaryUser getTemporaryUserById(Long id);
}
