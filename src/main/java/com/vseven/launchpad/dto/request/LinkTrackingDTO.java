package com.vseven.launchpad.dto.request;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class LinkTrackingDTO {
    private Integer linkId;
    private Integer numOfClicks;

}