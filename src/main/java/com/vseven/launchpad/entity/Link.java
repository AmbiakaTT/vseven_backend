package com.vseven.launchpad.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity(name = "link")
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private int group_id;
    @Getter
    private String url;

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    /*
    @OneToMany(mappedBy = "link")
    private List<UserQuickLink> quickLinks;
                                                  

     */

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", url='" + url + '\'' +
                '}';
    }
}
