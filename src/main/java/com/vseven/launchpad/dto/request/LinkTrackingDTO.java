package com.vseven.launchpad.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
//@Setter
@ToString
@Data
public class LinkTrackingDTO {
    private Integer linkId;
    private Integer numOfClicks;

}