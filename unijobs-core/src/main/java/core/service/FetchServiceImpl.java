package core.service;

import core.model.Skill;
import core.model.UniUser;
import core.repository.SkillRepository;
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

    UniUserRepository uniUserRepository;

    @Override
    public List<Skill> getAllSkill() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findSkill(Long id) {
        return skillRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<UniUser> getAllUsers() {
        log.trace("getAll");

        List<UniUser> users = uniUserRepository.findAll();

        log.trace("getAll: users = {}", users);

        return users;
    }
}
