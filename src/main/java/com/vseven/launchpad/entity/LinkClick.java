package com.vseven.launchpad.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LinkClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="link_click_id")
    private Integer linkClickId;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "link_id", referencedColumnName = "link_id"),
    })
    private Link link;


    @Column(name="num_of_clicks")
    private Integer numOfClicks;
}
