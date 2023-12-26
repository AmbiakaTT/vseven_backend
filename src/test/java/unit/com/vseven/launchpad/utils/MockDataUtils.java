package unit.com.vseven.launchpad.utils;

import com.vseven.launchpad.dto.request.CombinedDTO;
import com.vseven.launchpad.dto.request.LinkOrderDTO;
import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.dto.request.SectionOrderDTO;
import com.vseven.launchpad.dto.response.*;
import com.vseven.launchpad.entity.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MockDataUtils {
    public static Integer MOCK_LINK_ID = 1;
    public static Integer MOCK_SECTION_ID = 1;
    public static String MOCK_URL = "url";
    public static String MOCK_LINK_NAME = "link name";
    public static String MOCK_SECTION_NAME = "section name";
    public static Integer MOCK_LINK_ORDER = 1;
    public static Integer MOCK_SECTION_ORDER = 1;
    public static String MOCK_USER_NAME = "test user";
    public static Integer MOCK_USER_ID = 1;
    public static User MOCK_USER = User.builder()
            .userId(MOCK_USER_ID)
            .userName(MOCK_USER_NAME)
            .build();

    // Link Entity
    public static Link MOCK_LINK = Link.builder()
            .linkId(MOCK_LINK_ID)
            .url(MOCK_URL)
            .linkName(MOCK_LINK_NAME)
            .sectionId(MOCK_SECTION_ID)
            .build();
    public static Optional<Link> MOCK_LINK_OPTIONAL = Optional.of(MOCK_LINK);

    // UserQuickLink entity
    public static UserQuickLink MOCK_USER_QUICK_LINK = UserQuickLink.builder()
            .link(MOCK_LINK)
            .user(MOCK_USER)
            .build();
    public static List<UserQuickLink> MOCK_USER_QUICK_LINK_LIST = Arrays.asList(MOCK_USER_QUICK_LINK);
    public static Optional<UserQuickLink> MOCK_USER_QUICK_LINK_OPTIONAL = Optional.of(MOCK_USER_QUICK_LINK);

    // Section Entity
    public static Section MOCK_SECTION = Section.builder()
            .sectionId(MOCK_SECTION_ID)
            .sectionName(MOCK_SECTION_NAME)
            .build();
    public static Optional<Section> MOCK_SECTION_OPTIONAL = Optional.of(MOCK_SECTION);

    //SectionOrder entity
    public static SectionOrder MOCK_SECTION_ORDER_ENTITY = SectionOrder.builder()
            .section(MOCK_SECTION)
            .sectionOrder(MOCK_SECTION_ORDER)
            .user(MOCK_USER)
            .build();
    public static List<SectionOrder> MOCK_SECTION_ORDER_LIST = Arrays.asList(MOCK_SECTION_ORDER_ENTITY);

    // LinkOrder entity
    public static LinkOrder MOCK_LINK_ORDER_ENTITY = LinkOrder.builder()
            .link(MOCK_LINK)
            .linkOrder(MOCK_LINK_ORDER)
            .section(MOCK_SECTION)
            .build();
    public static List<LinkOrder> MOCK_LINK_ORDER_LIST = Arrays.asList(MOCK_LINK_ORDER_ENTITY);

    // DTO (Request body)
    public static QuickLinkDTO MOCK_QUICK_LINK_DTO = QuickLinkDTO.builder()
            .linksId(Arrays.asList(MOCK_LINK_ID))
            .build();

    public static LinkOrderDTO MOCK_LINK_ORDER_DTO = LinkOrderDTO.builder()
            .sectionId(MOCK_SECTION_ID)
            .userId(MOCK_USER_ID)
            .linkId(MOCK_LINK_ID)
            .linkOrder(MOCK_LINK_ORDER)
            .build();

    public static SectionOrderDTO MOCK_SECTION_ORDER_DTO = SectionOrderDTO.builder()
            .sectionId(MOCK_SECTION_ID)
            .userId(MOCK_USER_ID)
            .order(MOCK_SECTION_ORDER)
            .build();

    public static CombinedDTO MOCK_COMBINED_DTO = CombinedDTO.builder()
            .quickLinkDTO(MOCK_QUICK_LINK_DTO)
            .linkOrderDTOList(Arrays.asList(MOCK_LINK_ORDER_DTO))
            .sectionOrderDTOList(Arrays.asList(MOCK_SECTION_ORDER_DTO))
            .build();

    // Response
    public static LinkResponse MOCK_LINK_RESPONSE = LinkResponse.builder()
            .linkId(MOCK_LINK_ID)
            .linkName(MOCK_LINK_NAME)
            .url(MOCK_URL)
            .build();
    public static LinkOrderResponse MOCK_LINK_ORDER_RESPONSE = LinkOrderResponse.builder()
            .sectionId(MOCK_SECTION_ID)
            .linkId(MOCK_LINK_ID)
            .linkName(MOCK_LINK_NAME)
            .linkOrder(MOCK_LINK_ORDER)
            .build();

    public static SectionOrderResponse MOCK_SECTION_ORDER_RESPONSE = SectionOrderResponse.builder()
            .sectionId(MOCK_SECTION_ID)
            .sectionName(MOCK_SECTION_NAME)
            .sectionOrder(MOCK_SECTION_ORDER)
            .build();

    public static List<LinkResponse> MOCK_QUICK_LINK = Arrays.asList(MOCK_LINK_RESPONSE);
    public static List<LinkOrderResponse> MOCK_LINK_ORDER_RESPONSE_LIST = Arrays.asList(MOCK_LINK_ORDER_RESPONSE);
    public static List<SectionOrderResponse> MOCK_SECTION_ORDER_RESPONSE_LIST = Arrays.asList(MOCK_SECTION_ORDER_RESPONSE);

    public static FullResponse MOCK_FULL_RESPONSE = FullResponse.builder()
            .userName(MOCK_USER_NAME)
            .quickLink(MOCK_QUICK_LINK)
            .linkOrderResponse(MOCK_LINK_ORDER_RESPONSE_LIST)
            .sectionOrderResponse(MOCK_SECTION_ORDER_RESPONSE_LIST)
            .build();

    public static MessageResponse MOCK_SAVE_MESSAGE_RESPONSE = MessageResponse.builder()
            .message("Successfully Saved")
            .build();

    public static MessageResponse MOCK_UNBOOKMARK_MESSAGE_RESPONSE = MessageResponse.builder()
            .message("Successful Unbookmark")
            .build();

    public static MessageResponse MOCK_RESET_MESSAGE_RESPONSE = MessageResponse.builder()
            .message("Successfully Reset")
            .build();



}
