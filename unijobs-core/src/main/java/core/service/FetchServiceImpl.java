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
public class FetchServiceImpl implements FetchService {
    private static final Logger log = LoggerFactory.getLogger(FetchService.class);

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    UniUserRepository uniUserRepository;

    @Autowired
    UniJobRepository uniJobRepository;


    @Autowired
    TemporaryUserRepository temporaryUserRepository;

    @Override
    public List<Skill> getAllSkills() {
        log.trace("getAllSkills()");
        List<Skill> skills = skillRepository.findAll();
        log.trace("getAllSkills: skills = {}", skills);

        return skills;
    }

    @Override
    public Skill findSkill(Long id) {
        log.trace("findSkill()");
        Skill skill = skillRepository.findOne(id);
        log.trace("findskill: skill = {}", skill);

        return skill;
    }

    @Override
    public List<Job> getAllJobs() {
        log.trace("getAllJobs()");
        List<Job> jobs = uniJobRepository.findAll();
        log.trace("getAllJobs: jobs = {}", jobs);

        return jobs;
    }

    @Override
    public Job findJob(int id) {
        log.trace("findJob()");
        Job job = uniJobRepository.findOne(id);
        log.trace("findJob: job = {}", job);

        return job;
    }

    @Override
    @Transactional
    public UniUser getUserByUsername(String username) {
        UniUser u = uniUserRepository.getUniUserByUsername(username);
        System.out.println("user from service" + u);
        return u;
    }

    @Override
    public UniUser getUserById(Integer id) {
        log.trace("Get user by id : id={}",id);
        UniUser uniUser = uniUserRepository.getUniUserById(id);
        log.trace("user returned by id={} is user={}",id,uniUser);
        return uniUser;
    }


    @Override
    public List<UniUser> getAllUsers() {
        log.trace("getAll");
        List<UniUser> users = uniUserRepository.findAll();
        log.trace("getAll: users = {}", users);
        return users;
    }

    public List<TemporaryUser> getAllTemporaryUsers() {
        log.trace("getAll");
        List<TemporaryUser> tempUsers = temporaryUserRepository.findAll();
        log.trace("getAll: users = {}", tempUsers);

        return tempUsers;
    }

    @Override
    public TemporaryUser getTemporaryUserById(Long id){
        return temporaryUserRepository.findOne(id);
    }
}
