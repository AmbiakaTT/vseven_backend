package com.vseven.launchpad.dto.request;


import lombok.Data;

@Data
public class LinkOrderDTO {

    private Integer sectionId;

    private Integer userId;

    private Integer linkId;

    private Integer linkOrder;
}
