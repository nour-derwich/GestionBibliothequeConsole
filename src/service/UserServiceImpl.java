package service;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();
    private long userIdCounter = 1;

    @Override
    public User registerUser(String username, String password, String email) {
        User user = new User(userIdCounter++, username, password, email);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> loginUser(String username, String password) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
}
