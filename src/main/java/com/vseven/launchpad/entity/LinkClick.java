package com.vseven.launchpad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "LinkClicks")
public class LinkClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="link_click_id")
    private Integer linkClickId;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "link_id", referencedColumnName = "link_id")
    private Link link;


    @Column(name="num_of_clicks")
    private Integer numOfClicks;
}
