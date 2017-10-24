package core.service;

import core.model.Skill;
import core.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchServiceImpl implements FetchService {

    @Autowired
    SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkill() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findSkill(Long id) {
        return skillRepository.findOne(id);
    }
}
