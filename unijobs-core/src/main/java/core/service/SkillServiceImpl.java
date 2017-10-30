package core.service;

import core.model.Skill;
import core.repository.SkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex on 10/30/2017.
 */
@Service
public class SkillServiceImpl implements SkillService {

    private static final Logger log = LoggerFactory.getLogger(SkillService.class);

    @Autowired
    private SkillRepository skillRepository;

    @Override
    @Transactional
    public List<Skill> getAll() {
        log.trace("[SKILL] Entered getAll()");
        List<Skill> res = skillRepository.findAll();
        log.trace("[SKILL] Finished getAll()");
        return res;
    }

    @Override
    public Skill getOne(int id) {
        log.trace("[SKILL] Entered findOne({})", id);
        Skill res = skillRepository.findOne((long)id);
        log.trace("[SKILL] Finished findOne({}) = {}", id, res);
        return res;
    }

    @Override
    public void insert(Skill skill) {
        log.trace("[SKILL] Inserting skill {}", skill);
        skillRepository.save(skill);
        log.trace("[SKILL] Inserted");
    }

    @Override
    @Transactional
    public void update(int id, Skill skill) {
        log.trace("updateSkill: skill={}", skill);

        Skill skillUpdated = skillRepository.findOne((long)id);
        skillUpdated.setDescription(skill.getDescription());
        skillUpdated.setJobs(skill.getJobs());
        skillUpdated.setProviders(skill.getProviders());

        log.trace("updatedSkill: skill={}", skillUpdated);
    }
}