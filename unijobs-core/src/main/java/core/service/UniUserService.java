package core.service;

import core.model.UniUser;

import java.util.List;

/**
 * Created by Alex on 11/13/2017.
 */
public interface UniUserService {

    UniUser getUserByUsername(String username);
    UniUser getUserById(Integer id);
    UniUser getUserByEmail(String email);

    List<UniUser> getAllUsers();
    void addUser(UniUser user);
    void updateUser(UniUser user);
    void clear();

    List<Review> getReviewsForUserId(Integer userId);
}
