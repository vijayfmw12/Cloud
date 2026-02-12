package com.tripurahomes.residentsportal.controller;

import com.tripurahomes.residentsportal.model.User;
import com.tripurahomes.residentsportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    // Show the registration page
    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";  // Maps to templates/register.html
    }

    // Handle the registration form submission
    @PostMapping("/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirmPassword") String confirmPassword,
                               @RequestParam("email") String email,
                               Model model) {
        // Validate that password and confirmPassword match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "register";  // Stay on the registration page if passwords don't match
        }

        // Check if username already exists
        if (userService.findByUsername(username) != null) {
            model.addAttribute("error", "Username already exists.");
            return "register";  // Stay on the registration page if username exists
        }

        // Check if email is valid (basic check)
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            model.addAttribute("error", "Invalid email format.");
            return "register";  // Stay on the registration page if email is invalid
        }

        // Encode password using BCrypt
        String encodedPassword = passwordEncoder.encode(password);

        // Create new user and save to the database
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setEmail(email);  // Set the email
        user.setRole("ROLE_USER"); // Default role for new users
        user.setEnabled(true); // Enable the user by default
        userService.saveUser(user);

        model.addAttribute("message", "Registration successful! Please log in.");
        return "redirect:/login";  // Redirect to login page after successful registration
    }
}
