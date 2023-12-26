package com.vseven.launchpad.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="LinkOrder")
public class LinkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="link_order_id")
    private Integer linkOrderId;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @Getter
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "link_id", referencedColumnName = "link_id"),
    })
    private Link link;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @Getter
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "section_id", referencedColumnName = "section_id"),
    })
    private Section section;

    @ManyToOne
    @JsonIgnore
    @JsonManagedReference
    @Getter
    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name="link_order")
    private Integer linkOrder;
}
