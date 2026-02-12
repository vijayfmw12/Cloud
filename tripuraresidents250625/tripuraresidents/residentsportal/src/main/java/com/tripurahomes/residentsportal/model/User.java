package com.tripurahomes.residentsportal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String password; // This will be BCrypt hash

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private String role = "ROLE_USER"; // Default role for new users

    @Column(name = "resident_id")
    private Integer residentId;

    @Column(name = "created_on", updatable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false, unique = true)
    private String email;

    @PrePersist
    public void prePersist() {
        this.createdOn = LocalDateTime.now();
    }
}
