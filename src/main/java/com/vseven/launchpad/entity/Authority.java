package com.vseven.launchpad.entity;

import jakarta.persistence.*;

import jakarta.persistence.Entity;

@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authority_id;

    @Column(name="username")
    private String username; // Represents the authority name (e.g., "ROLE_USER")

    @Column(name="user_id")
    private int user_id;

    @Column(name="authority")
    private String authority;

    public Authority() {

    }
    public Authority(int authority_id, String name, int user_id, String authority) {
        this.authority_id = authority_id;
        this.username = name;
        this.user_id = user_id;
        this.authority = authority;
    }


    public int getAuthority_id() {
        return authority_id;
    }

    public void setAuthority_id(int authority_id) {
        this.authority_id = authority_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
