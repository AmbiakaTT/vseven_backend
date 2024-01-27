package unit.com.vseven.launchpad.dto.request;

import com.vseven.launchpad.dto.request.CombinedDTO;
import com.vseven.launchpad.dto.request.LinkOrderDTO;
import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.dto.request.SectionOrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
@ExtendWith(MockitoExtension.class)
public class CombinedDTOTest {
    @Test
    void testGettersAndSetters() {
        CombinedDTO combinedDTO = new CombinedDTO();

        QuickLinkDTO quickLinkDTO = QuickLinkDTO.builder().linksId(Arrays.asList("1", "2", "3")).build();
        List<SectionOrderDTO> sectionOrderDTOList = Collections.singletonList(
                SectionOrderDTO.builder().sectionId("1").userId(2).column(1).index(1).build()
        );
        List<LinkOrderDTO> linkOrderDTOList = Collections.singletonList(
                LinkOrderDTO.builder().sectionId("4").userId(5).linkId("6").linkOrder(7).build()
        );

        combinedDTO.setQuickLinkDTO(quickLinkDTO);
        combinedDTO.setSectionOrderDTOList(sectionOrderDTOList);
        combinedDTO.setLinkOrderDTOList(linkOrderDTOList);

        assertEquals(quickLinkDTO, combinedDTO.getQuickLinkDTO());
        assertEquals(sectionOrderDTOList, combinedDTO.getSectionOrderDTOList());
        assertEquals(linkOrderDTOList, combinedDTO.getLinkOrderDTOList());
    }

    @Test
    void testEqualsAndHashCode() {
        QuickLinkDTO quickLinkDTO = QuickLinkDTO.builder().linksId(Arrays.asList("1", "2", "3")).build();
        List<SectionOrderDTO> sectionOrderDTOList = Collections.singletonList(
                SectionOrderDTO.builder().sectionId("1").userId(2).column(1).index(1).build()
        );
        List<LinkOrderDTO> linkOrderDTOList = Collections.singletonList(
                LinkOrderDTO.builder().sectionId("4").userId(5).linkId("6").linkOrder(7).build()
        );

        CombinedDTO combinedDTO1 = CombinedDTO.builder()
                .quickLinkDTO(quickLinkDTO)
                .sectionOrderDTOList(sectionOrderDTOList)
                .linkOrderDTOList(linkOrderDTOList)
                .build();

        CombinedDTO combinedDTO2 = CombinedDTO.builder()
                .quickLinkDTO(quickLinkDTO)
                .sectionOrderDTOList(sectionOrderDTOList)
                .linkOrderDTOList(linkOrderDTOList)
                .build();

        CombinedDTO differentCombinedDTO = CombinedDTO.builder().build();

        // Test equality
        assertEquals(combinedDTO1, combinedDTO2);
        assertEquals(combinedDTO1.hashCode(), combinedDTO2.hashCode());

        // Test inequality
        assertNotEquals(combinedDTO1, differentCombinedDTO);
        assertNotEquals(combinedDTO1.hashCode(), differentCombinedDTO.hashCode());
    }

    @Test
    void testToString() {
        QuickLinkDTO quickLinkDTO = QuickLinkDTO.builder().linksId(Arrays.asList("1", "2", "3")).build();
        List<SectionOrderDTO> sectionOrderDTOList = Collections.singletonList(
                SectionOrderDTO.builder().sectionId("1").userId(2).column(1).index(1).build()
        );
        List<LinkOrderDTO> linkOrderDTOList = Collections.singletonList(
                LinkOrderDTO.builder().sectionId("4").userId(5).linkId("6").linkOrder(7).build()
        );

        CombinedDTO combinedDTO = CombinedDTO.builder()
                .quickLinkDTO(quickLinkDTO)
                .sectionOrderDTOList(sectionOrderDTOList)
                .linkOrderDTOList(linkOrderDTOList)
                .build();

        String expectedToString = "CombinedDTO(" +
                "quickLinkDTO=" + quickLinkDTO + ", " +
                "sectionOrderDTOList=" + sectionOrderDTOList + ", " +
                "linkOrderDTOList=" + linkOrderDTOList +
                ")";

        assertEquals(expectedToString, combinedDTO.toString());
    }
}
