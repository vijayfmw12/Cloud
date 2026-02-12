package com.tripurahomes.residentsportal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendResetPasswordEmail(String toEmail, String resetToken) throws MessagingException {
        String resetLink = "http://localhost:8080/reset-password?token=" + resetToken;

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);  // `true` for multipart

        helper.setFrom(fromEmail);
        helper.setTo(toEmail);
        helper.setSubject("Password Reset Request");

        // Set plain text OR HTML content
        String emailContent = "<p>Hello,</p>"
                + "<p>To reset your password, click the link below:</p>"
                + "<p><a href=\"" + resetLink + "\">Reset Password</a></p>"
                + "<br><p>If you didn’t request a password reset, please ignore this email.</p>";

        helper.setText(emailContent, true); // true = HTML

        mailSender.send(message);
    }
}
