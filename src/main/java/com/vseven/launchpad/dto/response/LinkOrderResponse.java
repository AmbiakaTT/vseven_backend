package com.vseven.launchpad.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LinkOrderResponse {
    private Integer sectionId;

    private Integer linkId;

    private String linkName;

    private Integer linkOrder;

}
