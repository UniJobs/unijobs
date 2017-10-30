package core.service;

import core.model.Job;
import core.model.Skill;
import core.model.TemporaryUser;
import core.model.UniUser;
import core.repository.SkillRepository;
import core.repository.TemporaryUserRepository;
import core.repository.UniJobRepository;
import core.repository.UniUserRepository;
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
    private SkillRepository skillRepository;

    @Autowired
    private UniJobRepository uniJobRepository;

    @Autowired
    private TemporaryUserRepository temporaryUserRepository;

    @Override
    @Transactional
    public void addUser(UniUser user) {
        log.trace("createUser: user={}", user);

        user = uniUserRepository.save(user);

        log.trace("createUser: user={}", user);
    }

    @Override
    @Transactional
    public void updateUser(Long userId, UniUser user) {
        log.trace("updateUser: user={}", user);

        UniUser userUpdated = uniUserRepository.findOne(userId);
        userUpdated.setDob(user.getDob());
        userUpdated.setLastname(user.getLastname());
        userUpdated.setFirstname(user.getFirstname());
        userUpdated.setPassword(user.getPassword());

        log.trace("updatedUser: user={}", userUpdated);
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
