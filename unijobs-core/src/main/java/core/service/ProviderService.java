package core.service;

import core.model.Provider;
import core.model.Skill;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface ProviderService {
    List<Provider> getAll();
    Provider getOne(int id);
    void insert(Provider provider);

    void addSkill(int id, Skill skill);
}
