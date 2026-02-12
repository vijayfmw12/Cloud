package com.tripurahomes.residentsportal.service;

import com.tripurahomes.residentsportal.model.User;
import com.tripurahomes.residentsportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService; // Add reference to UserService to use Optional

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Now using Optional to fetch the user from the repository
        User user = userService.findByUsername(username);

        if (user == null) {
            System.out.println("User not found: " + username);
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Return UserDetails with correct enabled flag and authorities
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),      // use getPasswordHash(), assuming your entity field is password_hash
                user.isEnabled(),            // must return boolean true/false
                true,                       // accountNonExpired
                true,                       // credentialsNonExpired
                true,                       // accountNonLocked
                Collections.singletonList(
                    new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
                )
        );
    }
}
