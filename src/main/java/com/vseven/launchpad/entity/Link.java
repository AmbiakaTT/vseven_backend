package com.vseven.launchpad.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "links")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sectionId;
    private String url;
    private String linkName;


    /*
    @OneToMany(mappedBy = "link")
    private List<UserQuickLink> quickLinks;

     */
    
}
