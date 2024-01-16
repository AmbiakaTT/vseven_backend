package com.vseven.launchpad.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
//@Setter
@ToString
public class LinkOrderResponse {
    private String sectionId;

    private String linkId;

    private String linkName;

    private String url;

    private Integer linkOrder;

}
