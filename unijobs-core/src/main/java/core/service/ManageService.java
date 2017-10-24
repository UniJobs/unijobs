package core.service;

import core.model.Skill;
import org.springframework.stereotype.Service;

public interface ManageService {

    Skill addSkill(Skill skill);

    void removeSkill(Long id);


}
