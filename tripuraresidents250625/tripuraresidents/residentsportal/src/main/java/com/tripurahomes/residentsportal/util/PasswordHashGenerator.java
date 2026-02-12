package com.tripurahomes.residentsportal.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";  // change this to your desired password
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("BCrypt hash for '" + rawPassword + "' is:\n" + encodedPassword);
    }
}
