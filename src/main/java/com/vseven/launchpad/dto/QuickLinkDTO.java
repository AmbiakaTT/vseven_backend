package com.vseven.launchpad.dto;


import lombok.Data;

import java.util.List;

@Data
public class QuickLinkDTO {

    private String username;
    private List<Integer> linksId;

}
