package com.tripurahomes.residentsportal.controller;

import com.tripurahomes.residentsportal.model.User;
import com.tripurahomes.residentsportal.service.UserService;
import com.tripurahomes.residentsportal.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.mail.MessagingException;  // Corrected import to jakarta.mail
import jakarta.mail.internet.MimeMessage; // Corrected import to jakarta.mail.internet

@Controller
public class ForgotPasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String handleForgotPassword(@RequestParam("email") String email, Model model) {
        User user = userService.findByEmail(email);

        if (user != null) {
            String resetToken = "dummy-token";

            try {
                // Correct method name here
                emailService.sendResetPasswordEmail(email, resetToken);
                model.addAttribute("message", "Password reset instructions sent to your email.");
            } catch (MessagingException e) {
                model.addAttribute("message", "Failed to send email. Please try again later.");
                e.printStackTrace();
            }
        } else {
            model.addAttribute("message", "No user found with that email address.");
        }

        return "forgot-password";
    }
}
