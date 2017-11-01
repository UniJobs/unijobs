package core.service;

import core.model.Skill;

import java.util.List;

/**
 * Created by Alex on 10/30/2017.
 */
public interface SkillService {
    List<Skill> getAll();
    Skill getOne(int id);
    void insert(Skill skill);
    void update(int id, Skill skill);
    void clear();
}
