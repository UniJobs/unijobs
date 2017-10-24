package core.service;

import core.model.UniUser;
import core.repository.UniUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class UniUserServiceImpl implements UniUserService{
    private static final Logger log = LoggerFactory.getLogger(UniUserService.class);

    @Autowired
    private UniUserRepository userRepository;

    @Override
    @Transactional
    public List<UniUser> getAll() {
        log.trace("getAll");

        List<UniUser> users = userRepository.findAll();

        log.trace("getAll: users = {}", users);

        return users;
    }

    @Override
    @Transactional
    public void addUser(UniUser user) {
        log.trace("createUser: user={}", user);

        user = userRepository.save(user);

        log.trace("createUser: user={}", user);
    }

    @Override
    @Transactional
    public void updateUser(UniUser user) {
        log.trace("updateUser: user={}", user);

        userRepository.save(user);

        log.trace("updateUser: user={}", user);
    }
}
