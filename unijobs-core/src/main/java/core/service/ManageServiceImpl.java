package core.service;

import core.model.Skill;
import core.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManageServiceImpl implements ManageService{

    @Autowired
    SkillRepository skillRepository;

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void removeSkill(Long id) {
        skillRepository.delete(id);
    }

}
