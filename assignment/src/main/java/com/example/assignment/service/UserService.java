package com.example.assignment.service;

import com.example.assignment.model.User;
import com.example.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }

    public User createUser(User user) { return userRepository.save(user); }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setAge(userDetails.getAge());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) { userRepository.deleteById(id); }
}
