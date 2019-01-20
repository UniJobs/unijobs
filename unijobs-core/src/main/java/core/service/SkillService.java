package core.service;

import core.model.Skill;

import java.util.List;

/**
 * Created by Ionut on 10/30/2018.
 */
public interface SkillService {
    List<Skill> getAll();
    Skill getOne(int id);
    Skill getSkillById(Integer id);
    void insert(Skill skill);
    void update(int id, Skill skill);
    void clear();
    Skill getSkillByDescription(String description);
}
