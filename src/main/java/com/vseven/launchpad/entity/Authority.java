package com.vseven.launchpad.entity;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.*;

//test
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authority_id;

    @Column(name="username")
    private String userName; // Represents the authority name (e.g., "ROLE_USER")

    @Column(name="user_id")
    private int userId;

    @Column(name="authority")
    private String authority;


}
