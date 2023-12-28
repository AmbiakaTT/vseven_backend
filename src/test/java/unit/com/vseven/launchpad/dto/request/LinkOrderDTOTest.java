package unit.com.vseven.launchpad.dto.request;

import com.vseven.launchpad.dto.request.LinkOrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class LinkOrderDTOTest {
    @Test
    void testGettersAndSetters() {
        LinkOrderDTO linkOrderDTO = new LinkOrderDTO();

        linkOrderDTO.setSectionId(1);
        linkOrderDTO.setUserId(2);
        linkOrderDTO.setLinkId(3);
        linkOrderDTO.setLinkOrder(4);

        assertEquals(1, linkOrderDTO.getSectionId());
        assertEquals(2, linkOrderDTO.getUserId());
        assertEquals(3, linkOrderDTO.getLinkId());
        assertEquals(4, linkOrderDTO.getLinkOrder());
    }
    @Test
    void testEqualsAndHashCode() {
        LinkOrderDTO linkOrderDTO1 = LinkOrderDTO.builder()
                .sectionId(1)
                .userId(2)
                .linkId(3)
                .linkOrder(4)
                .build();

        LinkOrderDTO linkOrderDTO2 = LinkOrderDTO.builder()
                .sectionId(1)
                .userId(2)
                .linkId(3)
                .linkOrder(4)
                .build();

        LinkOrderDTO differentLinkOrderDTO = LinkOrderDTO.builder()
                .sectionId(5)
                .userId(6)
                .linkId(7)
                .linkOrder(8)
                .build();

        // Test equality
        assertEquals(linkOrderDTO1, linkOrderDTO2);
        assertEquals(linkOrderDTO1.hashCode(), linkOrderDTO2.hashCode());

        // Test inequality
        assertNotEquals(linkOrderDTO1, differentLinkOrderDTO);
        assertNotEquals(linkOrderDTO1.hashCode(), differentLinkOrderDTO.hashCode());
    }
    @Test
    void testToString() {
        LinkOrderDTO linkOrderDTO = LinkOrderDTO.builder()
                .sectionId(1)
                .userId(2)
                .linkId(3)
                .linkOrder(4)
                .build();

        String expectedToString = "LinkOrderDTO(sectionId=1, userId=2, linkId=3, linkOrder=4)";

        assertEquals(expectedToString, linkOrderDTO.toString());
    }

}
