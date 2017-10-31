package core.service;

import core.model.Provider;
import core.model.Skill;
import core.repository.ProviderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
@Service
public class ProviderServiceImpl implements ProviderService{

    private static final Logger log = LoggerFactory.getLogger(ProviderService.class);

    @Autowired
    private ProviderRepository providerRepository;

    @Override
    public List<Provider> getAll() {
        log.trace("provider service - get all providers");
        List<Provider> res = providerRepository.findAll();
        log.trace("provider service - got them all");
        return res;
    }

    @Override
    public Provider getOne(int id) {
        log.trace("provider service - get one {}", id);
        Provider res = providerRepository.findOne(id);
        log.trace("provicer service - got him");
        return res;
    }

    @Override
    public void insert(Provider provider) {
        log.trace("provider service - insert one");
        providerRepository.save(provider);
        log.trace("provider service - inserted him");
    }

    @Override
    @Transactional
    public void addSkill(int id, Skill skill) {
        log.trace("provider service - add new skill");
        Provider old = providerRepository.findOne(id);
        old.addSkill(skill);
        log.trace("provider service - added skill");
    }

}
