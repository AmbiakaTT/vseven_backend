package com.vseven.launchpad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
@Entity(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer userId;

    @Column(name="user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name="password_hash")
    private String passwordHash;

    @Column(name = "enabled")
    private Integer enabled;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<UserQuickLink> quickLinks;
    
    @JsonIgnore
    @JsonManagedReference
    public List<UserQuickLink> getQuickLinks() {
        return quickLinks;
    }
    
}
