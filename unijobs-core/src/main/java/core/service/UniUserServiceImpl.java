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
    public UniUser createUser(String username, String password, String email) {
        log.trace("createUser: username={}, password={}, email={}", username, password, email);

        UniUser user = UniUser.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();

        user = userRepository.save(user);

        log.trace("createUser: user={}", user);

        return user;
    }

    @Override
    @Transactional
    public UniUser updateUser(Long userId, String password, String email, String firstName, String lastName, Date dob) {
        log.trace("updateUser: userID={}, password={}, email={}, firstName={}, lastName={}, dob={}",
                userId, password, email, firstName, lastName, dob);

        log.trace("resultFindOne: ", userRepository.findOne(userId));
        UniUser user = userRepository.findOne(userId);
        user.setPassword(password);
        user.setEmail(email);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setDob(dob);

        log.trace("updateUser: user={}", user);

        return user;
    }
}
