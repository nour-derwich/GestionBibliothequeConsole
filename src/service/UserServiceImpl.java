package service;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private List<User> users = new ArrayList<>();
    private Long userIdCounter = 1L;

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

    @Override
    public void updateUser(Long id, String username, String email, String password) {
        users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .ifPresent(user -> {
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPassword(password);
                });
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
