package unit.com.vseven.launchpad.dto.request;

import com.vseven.launchpad.dto.request.QuickLinkDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class QuickLinkDTOTest {
    @Test
    void testGettersAndSetters() {
        QuickLinkDTO quickLinkDTO = new QuickLinkDTO();

        List<Integer> linksId = Arrays.asList(1, 2, 3);

        quickLinkDTO.setLinksId(linksId);

        assertEquals(linksId, quickLinkDTO.getLinksId());
    }

    @Test
    void testEqualsAndHashCode() {
        List<Integer> linksId1 = Arrays.asList(1, 2, 3);
        List<Integer> linksId2 = Arrays.asList(1, 2, 3);
        List<Integer> differentLinksId = Arrays.asList(4, 5, 6);

        QuickLinkDTO quickLinkDTO1 = QuickLinkDTO.builder().linksId(linksId1).build();
        QuickLinkDTO quickLinkDTO2 = QuickLinkDTO.builder().linksId(linksId2).build();
        QuickLinkDTO differentQuickLinkDTO = QuickLinkDTO.builder().linksId(differentLinksId).build();

        // Test equality
        assertEquals(quickLinkDTO1, quickLinkDTO2);
        assertEquals(quickLinkDTO1.hashCode(), quickLinkDTO2.hashCode());

        // Test inequality
        assertNotEquals(quickLinkDTO1, differentQuickLinkDTO);
        assertNotEquals(quickLinkDTO1.hashCode(), differentQuickLinkDTO.hashCode());
    }

    @Test
    void testToString() {
        List<Integer> linksId = Arrays.asList(1, 2, 3);

        QuickLinkDTO quickLinkDTO = QuickLinkDTO.builder().linksId(linksId).build();

        String expectedToString = "QuickLinkDTO(linksId=" + linksId + ")";

        assertEquals(expectedToString, quickLinkDTO.toString());
    }
}
