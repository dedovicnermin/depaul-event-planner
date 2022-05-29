package io.depaul.depauleventplanner.repo.user;

import io.depaul.depauleventplanner.model.user.User;

import java.util.List;

public interface UserRepository {
    void save(User user);
    User getUser(String username);
    List<User> all();
}
