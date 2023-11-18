package com.vseven.launchpad.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "userquicklink")
public class UserQuickLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer userQuickLinkId;

    @ManyToOne
    @JsonIgnore
    @JsonBackReference
    @JoinColumns({
            @JoinColumn(name = "user_id", referencedColumnName = "id"),
            @JoinColumn(name = "user_name", referencedColumnName = "user_name") ,
    })
    private User user;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter
    @NotNull
    @JoinColumns({
            @JoinColumn(name = "link_id", referencedColumnName = "id"),
            @JoinColumn(name = "url", referencedColumnName = "url") ,
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
}
