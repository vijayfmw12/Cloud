package com.tripurahomes.residentsportal.service;

import com.tripurahomes.residentsportal.model.User;
import com.tripurahomes.residentsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register the user
    public void registerUser(User user) {
        // Check if username or email already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists.");
        }

        // Encrypt the password (assuming password hashing logic here)
        user.setPassword("encryptedPassword"); // BCrypt encryption should happen here

        userRepository.save(user);
    }

    // Find user by username (unwrap Optional)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    // Find user by email (unwrap Optional)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

    // Save user to the database
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
