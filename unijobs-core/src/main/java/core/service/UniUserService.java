package core.service;

import core.model.Review;
import core.model.UniUser;

import java.util.List;

/**
 * Created by Ionut on 11/13/2018.
 */
public interface UniUserService {

    UniUser getUserByUsername(String username);
    UniUser getUserById(Integer id);
    UniUser getUserByEmail(String email);

    List<UniUser> getAllUsers();
    void addUser(UniUser user);
    void updateUser(UniUser user);
    void clear();
}
