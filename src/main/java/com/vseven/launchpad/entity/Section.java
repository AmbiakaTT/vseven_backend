package com.vseven.launchpad.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="section_id")
    private Integer sectionId;

    @Column(name="section_name")
    private String sectionName;
}
