package com.vseven.launchpad.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "url")
    private String url;

    @Column(name = "link_name")
    private String linkName;

    @JsonManagedReference
    @OneToMany(mappedBy = "link", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserQuickLink> quickLinks;
     
}
