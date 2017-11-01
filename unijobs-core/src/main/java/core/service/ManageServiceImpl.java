package core.service;

import core.model.*;
import core.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManageServiceImpl implements ManageService{
    private static final Logger log = LoggerFactory.getLogger(ManageService.class);

    @Autowired
    private UniUserRepository uniUserRepository;

    @Autowired
    private UniJobRepository uniJobRepository;

    @Autowired
    private TemporaryUserRepository temporaryUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void addUser(UniUser user) {
        log.trace("createUser: user={}", user);

        user = uniUserRepository.save(user);

        log.trace("createUser: user={}", user);
    }

    @Override
    @Transactional
    public void updateUser(UniUser user) {
        log.trace("updateUser: user={}", user);

        uniUserRepository.save(user);
        log.trace("updatedUser: user={}", user);
    }

    @Override
    public void addJob(Job job) {
        log.trace("addJob: job = {}", job);

        uniJobRepository.save(job);

        log.trace("addJob: job = {}", job);

    }

    @Override
    public void updateJob(Job job) {

        log.trace("updateJob: job = {}", job);

        uniJobRepository.save(job);

        log.trace("updateJob: job = {}", job);

    }

    @Override
    public void clearJobs() {
        log.trace("clear Jobs");
        uniJobRepository.deleteAll();
        log.trace("Jobs cleared");
    }


    @Override
    public void addTemporaryUser(TemporaryUser temporaryUser){
        log.trace("addTemporaryUser temporaryUser= {}", temporaryUser);

        temporaryUserRepository.save(temporaryUser);

        log.trace("addTemporaryUser: temporaryUser = {}", temporaryUser);
    }

    @Override
    public void removeTemporaryUser(TemporaryUser temporaryUser){
        temporaryUserRepository.delete(temporaryUser);
    }
}
