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
@Entity(name="SectionOrder")
public class SectionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="section_order_id")
    private Integer sectionOrderId;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @Getter
    @NotNull
    @JoinColumn(name = "section_id", referencedColumnName = "section_id")
    private Section section;

    @ManyToOne
    @JsonIgnore
    @JsonManagedReference
    @Getter
    @NotNull
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Column(name="section_index")
    private Integer index;

    @Column(name="section_column")
    private Integer column;
}
