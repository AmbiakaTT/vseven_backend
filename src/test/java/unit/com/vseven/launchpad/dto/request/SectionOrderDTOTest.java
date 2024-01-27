package unit.com.vseven.launchpad.dto.request;

import com.vseven.launchpad.dto.request.SectionOrderDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class SectionOrderDTOTest {
//    @Test
//    void testGettersAndSetters() {
//        SectionOrderDTO sectionOrderDTO = new SectionOrderDTO();
//
//        sectionOrderDTO.setSectionId(String.valueOf(1));
//        sectionOrderDTO.setUserId(2);
//        sectionOrderDTO.setOrder(3);
//
//        assertEquals("1", sectionOrderDTO.getSectionId());
//        assertEquals(2, sectionOrderDTO.getUserId());
//        assertEquals(3, sectionOrderDTO.getOrder());
//    }
//    @Test
//    void testEqualsAndHashCode() {
//        SectionOrderDTO sectionOrderDTO1 = SectionOrderDTO.builder()
//                .sectionId(1)
//                .userId(2)
//                .order(3)
//                .build();
//
//        SectionOrderDTO sectionOrderDTO2 = SectionOrderDTO.builder()
//                .sectionId(1)
//                .userId(2)
//                .order(3)
//                .build();
//
//        SectionOrderDTO differentSectionOrderDTO = SectionOrderDTO.builder()
//                .sectionId(4)
//                .userId(5)
//                .order(6)
//                .build();
//
//        // Test equality
//        assertEquals(sectionOrderDTO1, sectionOrderDTO2);
//        assertEquals(sectionOrderDTO1.hashCode(), sectionOrderDTO2.hashCode());
//
//        // Test inequality
//        assertNotEquals(sectionOrderDTO1, differentSectionOrderDTO);
//        assertNotEquals(sectionOrderDTO1.hashCode(), differentSectionOrderDTO.hashCode());
//    }
//
//    @Test
//    void testToString() {
//        SectionOrderDTO sectionOrderDTO = SectionOrderDTO.builder()
//                .sectionId(1)
//                .userId(2)
//                .order(3)
//                .build();
//
//        String expectedToString = "SectionOrderDTO(sectionId=1, userId=2, order=3)";
//
//        assertEquals(expectedToString, sectionOrderDTO.toString());
//    }

    @Test
    void testGettersAndSetters() {
        SectionOrderDTO sectionOrderDTO = new SectionOrderDTO();

        sectionOrderDTO.setSectionId("1");
        sectionOrderDTO.setUserId(2);
        sectionOrderDTO.setIndex(3);
        sectionOrderDTO.setColumn(4);

        assertEquals("1", sectionOrderDTO.getSectionId());
        assertEquals(2, sectionOrderDTO.getUserId());
        assertEquals(3, sectionOrderDTO.getIndex());
        assertEquals(4, sectionOrderDTO.getColumn());
    }

    @Test
    void testEqualsAndHashCode() {
        SectionOrderDTO sectionOrderDTO1 = SectionOrderDTO.builder()
                .sectionId("1")
                .userId(2)
                .index(3)
                .column(4)
                .build();

        SectionOrderDTO sectionOrderDTO2 = SectionOrderDTO.builder()
                .sectionId("1")
                .userId(2)
                .index(3)
                .column(4)
                .build();

        SectionOrderDTO differentSectionOrderDTO = SectionOrderDTO.builder()
                .sectionId("5")
                .userId(6)
                .index(7)
                .column(8)
                .build();

        // Test equality
        assertEquals(sectionOrderDTO1, sectionOrderDTO2);
        assertEquals(sectionOrderDTO1.hashCode(), sectionOrderDTO2.hashCode());

        // Test inequality
        assertNotEquals(sectionOrderDTO1, differentSectionOrderDTO);
        assertNotEquals(sectionOrderDTO1.hashCode(), differentSectionOrderDTO.hashCode());
    }

    @Test
    void testToString() {
        SectionOrderDTO sectionOrderDTO = SectionOrderDTO.builder()
                .sectionId("1")
                .userId(2)
                .index(3)
                .column(4)
                .build();

        String expectedToString = "SectionOrderDTO(sectionId=1, userId=2, index=3, column=4)";

        assertEquals(expectedToString, sectionOrderDTO.toString());
    }
}
