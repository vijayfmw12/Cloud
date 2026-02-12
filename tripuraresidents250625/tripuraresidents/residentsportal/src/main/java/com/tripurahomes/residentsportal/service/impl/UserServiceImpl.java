package com.tripurahomes.residentsportal.service.impl;

import com.tripurahomes.residentsportal.model.User;
import com.tripurahomes.residentsportal.repository.UserRepository;
import com.tripurahomes.residentsportal.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        logger.debug("💾 [DEBUG] Saving user to DB: username='{}', email='{}'", user.getUsername(), user.getEmail());
        System.out.println("➡️ SystemOut: Saving user: " + user.getUsername());
        userRepository.save(user);
    }

    // Add other methods as needed (e.g., findByUsername)
}
