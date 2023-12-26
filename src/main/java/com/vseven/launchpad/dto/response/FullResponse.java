package com.vseven.launchpad.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vseven.launchpad.entity.User;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
//@Setter
@ToString
public class FullResponse {
    private String userName;

    private List<LinkResponse> quickLink;

    private List<LinkOrderResponse> linkOrderResponse;

    private List<SectionOrderResponse> sectionOrderResponse;
}
