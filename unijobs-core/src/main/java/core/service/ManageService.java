package core.service;

import core.model.Job;
import core.model.Skill;
import core.model.TemporaryUser;
import core.model.UniUser;
import org.springframework.stereotype.Service;

public interface ManageService {

    void addUser(UniUser user);

    void updateUser(Long userId, UniUser user);

    void addJob(Job job);

    void updateJob(Job job);

    void addTemporaryUser(TemporaryUser temporaryUser);

    void removeTemporaryUser(TemporaryUser temporaryUser);
}
