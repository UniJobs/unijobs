package core.service;

import core.model.Skill;
import core.model.UniUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FetchService {
    List<Skill> getAllSkill();

    Skill findSkill(Long id);

    List<UniUser> getAllUsers();
}
