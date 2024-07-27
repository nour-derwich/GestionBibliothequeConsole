package service;

import model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(String username, String password, String email);
    Optional<User> loginUser(String username, String password);
}
