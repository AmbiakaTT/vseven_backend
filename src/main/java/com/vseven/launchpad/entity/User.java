package com.vseven.launchpad.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password_hash;
    private int enabled;
    public enum AuthenticationType {
        LOCAL,
        SSO
    }
    private AuthenticationType authenticationType;

    // Constructors, getters, and setters
    public User() {
        // Default constructor
    }


    /*
    @OneToMany(mappedBy = "user")
    private List<UserQuickLink> quickLinks;
           */

    public User(String username, String email, String the_password_hash) {
        this.username = username;
        this.email = email;
        this.password_hash = the_password_hash;
    }

    // Getters and setters for attributes

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password_hash;
    }

    public void setPassword(String password) {
        this.password_hash = password;
    }
}
