package core.service;

import core.model.Review;
import core.model.UniUser;
import core.repository.ReviewRepository;
import core.repository.UniUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ionut on 11/13/2018.
 */
@Service
public class UniUserServiceImpl implements UniUserService{

    private static final Logger log = LoggerFactory.getLogger(UniUserService.class);

    @Autowired
    UniUserRepository uniUserRepository;

    @Override
    @Transactional
    public UniUser getUserByUsername(String username) {
        UniUser u = uniUserRepository.getUniUserByUsername(username);
        System.out.println("user from service" + u);
        return u;
    }

    @Override
    public UniUser getUserById(Integer id) {
        log.trace("Get user by id : id={}",id);
        UniUser uniUser = uniUserRepository.getUniUserById(id);
        log.trace("user returned by id={} is user={}",id,uniUser);
        return uniUser;
    }

    @Override
    public UniUser getUserByEmail(String email) {
        List<UniUser> users = uniUserRepository.findByEmail(email);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public List<UniUser> getAllUsers() {
        log.trace("getAll");
        List<UniUser> users = uniUserRepository.findAll();
        log.trace("getAll: users = {}", users);
        return users;
    }

    @Override
    @Transactional
    public void addUser(UniUser user) {
        log.trace("createUser: user={}", user);

        user = uniUserRepository.save(user);

        log.trace("createUser: user={}", user);
    }

    @Override
    @Transactional
    public void updateUser(UniUser user) {
        log.trace("updateUser: user={}", user);

        uniUserRepository.save(user);
        log.trace("updatedUser: user={}", user);
    }

    @Override
    public void clear() {
        log.trace("remove all users begin");
        uniUserRepository.deleteAll();
        log.trace("remove all users end");
    }
}
