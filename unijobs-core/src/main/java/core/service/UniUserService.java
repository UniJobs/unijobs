package core.service;

import core.model.UniUser;

import java.util.List;

public interface UniUserService {
    List<UniUser> getAll();
    void addUser(UniUser user);
    void updateUser(UniUser user);
}
