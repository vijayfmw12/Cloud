package com.tripurahomes.residentsportal.repository;

import com.tripurahomes.residentsportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Find user by username, returns Optional<User> to handle potential null values
    Optional<User> findByUsername(String username);

    // Find user by email, returns Optional<User> to handle potential null values
    Optional<User> findByEmail(String email);
}
