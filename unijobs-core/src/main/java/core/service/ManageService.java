package core.service;

import core.model.Skill;
import core.model.UniUser;
import org.springframework.stereotype.Service;

public interface ManageService {

    Skill addSkill(Skill skill);

    void removeSkill(Long id);

    void addUser(UniUser user);

    void updateUser(UniUser user);
}
