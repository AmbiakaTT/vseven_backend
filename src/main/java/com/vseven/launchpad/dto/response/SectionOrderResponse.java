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
public class SectionOrderResponse {
    private String sectionId;

    private String sectionName;

    private Integer sectionOrder;

    private Integer index;

    private Integer column;
}
