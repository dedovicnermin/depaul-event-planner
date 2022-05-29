package io.depaul.depauleventplanner.repo.user;

import io.depaul.depauleventplanner.model.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserRepo implements UserRepository {
    private final Map<String, User> users;

    public UserRepo(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(users.values());
    }
}
