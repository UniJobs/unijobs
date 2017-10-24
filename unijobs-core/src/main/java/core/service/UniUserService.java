package core.service;

import core.model.UniUser;

import java.util.Date;
import java.util.List;

public interface UniUserService {
    List<UniUser> getAll();
    UniUser createUser(String username, String password, String email);
    UniUser updateUser(Long userId, String password, String email, String firstName, String lastName, Date dob);
}
