package core.service;

import core.model.Authority;
import core.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void addAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public void clear() {
        authorityRepository.deleteAll();
    }
}
