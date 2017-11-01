package core.service;

import core.model.*;
import org.springframework.stereotype.Service;

public interface ManageService {

    void addUser(UniUser user);
    void updateUser(UniUser user);

    void addJob(Job job);
    void updateJob(Job job);
    void clearJobs();

    void addTemporaryUser(TemporaryUser temporaryUser);
    void removeTemporaryUser(TemporaryUser temporaryUser);
}
