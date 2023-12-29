package com.vseven.launchpad.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
//@Setter
@ToString
public class LinkTrackingResponse {
    private List<LinkResponse> topLinks;
}
