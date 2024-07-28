package service;

import model.User;

import java.util.Optional;
import java.util.List;

public interface UserService {
    User registerUser(String username, String password, String email);
    Optional<User> loginUser(String username, String password); // Ensure this method is here
    void updateUser(Long id, String username, String email, String password);
    List<User> getAllUsers(); // Added for admin functionality
}
