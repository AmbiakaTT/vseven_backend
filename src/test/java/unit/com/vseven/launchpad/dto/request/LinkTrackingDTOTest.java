package unit.com.vseven.launchpad.dto.request;

import com.vseven.launchpad.dto.request.LinkTrackingDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class LinkTrackingDTOTest {

    @Test
    void testGettersAndSetters() {
        LinkTrackingDTO linkTrackingDTO = LinkTrackingDTO.builder()
                .linkId(1)
                .numOfClicks(10)
                .build();

        assertEquals(1, linkTrackingDTO.getLinkId());
        assertEquals(10, linkTrackingDTO.getNumOfClicks());

        // Test setters
        linkTrackingDTO.setLinkId(2);
        linkTrackingDTO.setNumOfClicks(20);

        assertEquals(2, linkTrackingDTO.getLinkId());
        assertEquals(20, linkTrackingDTO.getNumOfClicks());
    }

    @Test
    public void testEqualsAndHashCode_LinkTrackingDTO() throws Exception {
        LinkTrackingDTO link1 = LinkTrackingDTO.builder()
                .linkId(1)
                .numOfClicks(10)
                .build();

        LinkTrackingDTO link2 = LinkTrackingDTO.builder()
                .linkId(1)
                .numOfClicks(10)
                .build();

        LinkTrackingDTO differentLink = LinkTrackingDTO.builder()
                .linkId(2)
                .numOfClicks(20)
                .build();

        assertEquals(link1, link2);
        assertEquals(link1.hashCode(), link2.hashCode());

        assertNotEquals(link1, differentLink);
        assertNotEquals(link1.hashCode(), differentLink.hashCode());

    }

    @Test
    void testEqualsWithNull_LinkTrackingDTO() throws Exception {
        LinkTrackingDTO link = LinkTrackingDTO.builder()
                .linkId(1)
                .numOfClicks(10)
                .build();

        // Test with null
        assertNotEquals(link, null);
    }

    @Test
    void testEqualsWithDifferentType_LinkTrackingDTO() {
        LinkTrackingDTO link = LinkTrackingDTO.builder()
                .linkId(1)
                .numOfClicks(10)
                .build();

        // Test with a different type
        assertNotEquals(link, "This is a string");
    }

    @Test
    void testToString() {
        LinkTrackingDTO link = LinkTrackingDTO.builder()
                .linkId(1)
                .numOfClicks(10)
                .build();

        // Expected string representation based on your class structure
        String expectedToString = "LinkTrackingDTO(linkId=1, numOfClicks=10)";

        // Assert that the actual toString result matches the expected string
        assertEquals(expectedToString, link.toString());
    }


}
