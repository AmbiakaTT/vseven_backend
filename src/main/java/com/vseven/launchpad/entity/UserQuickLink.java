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
@Entity(name = "UserQuickLink")
public class UserQuickLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_quicklink_id")
    private Integer userQuickLinkId;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @Getter
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            @JoinColumn(name = "user_name", referencedColumnName = "user_name") ,
    })
    private User user;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "link_id", referencedColumnName = "link_id"),
            @JoinColumn(name = "link_name", referencedColumnName = "link_name") ,
    })
    private Link link;


    @Override
    public String toString() {
        return "UserQuickLink{" +
                "ID=" + getUserQuickLinkId() +
                ", UserID=" + getUser().getUserId() +
                ", UserName='" + getUser().getUserName() + '\'' +
                ", LinkID=" + getLink().getLinkId() +
                ", LinkName='" + getLink().getLinkName() + '\'' +
                ", LinkURL='" + getLink().getUrl() + '\'' +
                '}';
    }
    //test
}

