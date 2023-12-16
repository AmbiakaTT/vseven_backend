package com.vseven.launchpad.dto.request;

import jakarta.annotation.Nullable;
import lombok.Data;

import java.util.List;

@Data
public class CombinedDTO {

    @Nullable
    private QuickLinkDTO quickLinkDTO;

    @Nullable
    private List<SectionOrderDTO> sectionOrderDTOList;

    @Nullable
    private List<LinkOrderDTO> linkOrderDTOList;
}
