package com.vseven.launchpad.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CombinedDTO {

    @Nullable
    private QuickLinkDTO quickLinkDTO;

    @Nullable
    private List<SectionOrderDTO> sectionOrderDTOList;

    @Nullable
    private List<LinkOrderDTO> linkOrderDTOList;
}
