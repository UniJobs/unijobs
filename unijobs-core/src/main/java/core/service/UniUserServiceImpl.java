package core.service;

import core.model.Review;
import core.model.TemporaryUser;
import core.model.UniUser;
import core.repository.ReviewRepository;
import core.repository.TemporaryUserRepository;
import core.repository.UniUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by Alex on 11/13/2017.
 */
@Service
public class UniUserServiceImpl implements UniUserService{

    private static final Logger log = LoggerFactory.getLogger(UniUserService.class);

    @Autowired
    UniUserRepository uniUserRepository;

    @Autowired
    TemporaryUserRepository temporaryUserRepository;

    @Autowired
    ReviewRepository reviewRepository;

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
    public List<UniUser> getAllUsers() {
        log.trace("getAll");
        List<UniUser> users = uniUserRepository.findAll();
        log.trace("getAll: users = {}", users);
        return users;
    }

    public List<TemporaryUser> getAllTemporaryUsers() {
        log.trace("getAll");
        List<TemporaryUser> tempUsers = temporaryUserRepository.findAll();
        log.trace("getAll: users = {}", tempUsers);

        return tempUsers;
    }

    @Override
    public TemporaryUser getTemporaryUserById(Long id){
        return temporaryUserRepository.findOne(id);
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

    @Override
    public void addTemporaryUser(TemporaryUser temporaryUser){
        log.trace("addTemporaryUser temporaryUser= {}", temporaryUser);

        temporaryUserRepository.save(temporaryUser);

        log.trace("addTemporaryUser: temporaryUser = {}", temporaryUser);
    }

    @Override
    public void removeTemporaryUser(TemporaryUser temporaryUser){
        temporaryUserRepository.delete(temporaryUser);
    }

    @Override
    public List<Review> getReviewsForUserId(Integer userId){
        log.trace("Get reviews by user id : id={}",userId);
        List<Review> reviews = reviewRepository.getReviewsForUserId(userId);
        log.trace("Reviews returned for userId={} are reviews={}", userId, reviews);
        return reviews;
    }
}
