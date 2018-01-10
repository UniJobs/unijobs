package core.service;

import core.model.Review;
import core.model.TemporaryUser;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Alex on 11/13/2017.
 */
public interface UniUserService {

    UniUser getUserByUsername(String username);
    UniUser getUserById(Integer id);
    List<UniUser> getAllUsers();
    void addUser(UniUser user);
    void updateUser(UniUser user);
    void clear();

    List<TemporaryUser> getAllTemporaryUsers();
    TemporaryUser getTemporaryUserById(Long id);
    void addTemporaryUser(TemporaryUser temporaryUser);
    void removeTemporaryUser(TemporaryUser temporaryUser);
    List<Review> getReviewsForUserId(Integer userId);
}
