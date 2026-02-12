package com.tripurahomes.residentsportal.controller;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Homepage mapping for both "/" and "/home"
    @GetMapping({"/home"})
    public String home(Model model) {
        // Get the authenticated user's information after Google OAuth login
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            // Check if the user is authenticated and retrieve their details
            if (authentication.getPrincipal() instanceof OAuth2User) {
                OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
                model.addAttribute("user", oauthUser.getAttributes());  // Add user details to the model
            }
        }

        return "home";  // Maps to templates/home.html (display user details)
    }

    // Unified main landing page (with dropdown sections in index.html)
    @GetMapping({"/", "/index"})
    public String index() {
        return "index";  // Maps to templates/index.html
    }

    // Login page mapping
    @GetMapping("/login")
    public String login() {
        return "login";  // Maps to templates/login.html
    }
}
