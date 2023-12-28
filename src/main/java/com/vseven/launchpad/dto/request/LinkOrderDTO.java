package com.vseven.launchpad.dto.request;


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
@Data
public class LinkOrderDTO {

    private Integer sectionId;

    private Integer userId;

    private Integer linkId;

    private Integer linkOrder;
}
