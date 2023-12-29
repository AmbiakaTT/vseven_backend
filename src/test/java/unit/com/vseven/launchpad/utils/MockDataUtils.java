package unit.com.vseven.launchpad.utils;

import com.vseven.launchpad.dto.request.*;
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

    // User Entity
    public static String MOCK_USER_NAME = "test user";
    public static Integer MOCK_USER_ID = 1;
    public static String MOCK_USER_EMAIL = "example@vinuni.edu.vn";
    public static String MOCK_PWD_HASH = "password hash";
    public static User MOCK_USER_1 = User.builder()
            .userId(MOCK_USER_ID)
            .userName(MOCK_USER_NAME)
            .email(MOCK_USER_EMAIL)
            .passwordHash(MOCK_PWD_HASH)
            .enabled(1)
            //.quickLinks(MOCK_USER_QUICK_LINK_LIST)
            .build();
    public static User MOCK_USER_2 = User.builder()
            .userId(2)
            .userName("user2")
            .email("user2@vinuni.edu.vn")
            .passwordHash("password hash 2")
            .enabled(1)
            .build();
    public static UserResponse MOCK_USER_1_RESPONSE = UserResponse.builder()
            .userId(MOCK_USER_ID)
            .userName(MOCK_USER_NAME)
            .email(MOCK_USER_EMAIL)
            .passwordHash(MOCK_PWD_HASH)
            .enabled(1)
            .build();
    public static UserResponse MOCK_USER_2_RESPONSE = UserResponse.builder()
            .userId(2)
            .userName("user2")
            .email("user2@vinuni.edu.vn")
            .passwordHash("password hash 2")
            .enabled(1)
            .build();

    public static List<UserResponse> MOCK_USER_RESPONSE_LIST = Arrays.asList(MOCK_USER_1_RESPONSE, MOCK_USER_2_RESPONSE);

    public static List<User> MOCK_USER_LIST = Arrays.asList(MOCK_USER_1, MOCK_USER_2);

    public static Optional<User> MOCK_USER_OPTIONAL = Optional.of(MOCK_USER_1);
    public static Optional<UserResponse> MOCK_USER_RESPONSE_OPTIONAL = Optional.of(MOCK_USER_1_RESPONSE);

    // Link Entity
    public static Link MOCK_LINK = Link.builder()
            .linkId(MOCK_LINK_ID)
            .url(MOCK_URL)
            .linkName(MOCK_LINK_NAME)
            .sectionId(MOCK_SECTION_ID)
            .build();
    public static Optional<Link> MOCK_LINK_OPTIONAL = Optional.of(MOCK_LINK);

    // UserQuickLink entity
    public static Integer MOCK_USER_QUICK_LINK_ID = 1;
    public static UserQuickLink MOCK_USER_QUICK_LINK = UserQuickLink.builder()
            .link(MOCK_LINK)
            .user(MOCK_USER_1)
            .userQuickLinkId(MOCK_USER_QUICK_LINK_ID)
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
            .user(MOCK_USER_1)
            .build();
    public static List<SectionOrder> MOCK_SECTION_ORDER_LIST = Arrays.asList(MOCK_SECTION_ORDER_ENTITY);

    // LinkOrder entity
    public static LinkOrder MOCK_LINK_ORDER_ENTITY = LinkOrder.builder()
            .link(MOCK_LINK)
            .linkOrder(MOCK_LINK_ORDER)
            .section(MOCK_SECTION)
            .build();
    public static List<LinkOrder> MOCK_LINK_ORDER_LIST = Arrays.asList(MOCK_LINK_ORDER_ENTITY);

    // LinkClick entity
    public static Integer MOCK_NUM_OF_CLICKS = 100;
    public static LinkClick MOCK_LINK_CLICK = LinkClick.builder()
            .link(MOCK_LINK)
            .numOfClicks(MOCK_NUM_OF_CLICKS)
            .build();
    public static List<LinkClick> MOCK_LINK_CLICK_LIST = Arrays.asList(MOCK_LINK_CLICK);

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

    public static LinkTrackingDTO MOCK_LINK_TRACKING_DTO = LinkTrackingDTO.builder()
            .linkId(MOCK_LINK_ID)
            .numOfClicks(MOCK_NUM_OF_CLICKS)
            .build();

    public static List<LinkTrackingDTO> MOCK_LINK_TRACKING_DTO_LIST = Arrays.asList(MOCK_LINK_TRACKING_DTO);

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

    public static MessageResponse MOCK_UPDATE_LINK_MESSAGE_RESPONSE = MessageResponse.builder()
            .message("Link Tracking Successfully Updated")
            .build();

    // Link Tracking Response
    public static List<LinkResponse> MOCK_LINK_RESPONSE_LIST = Arrays.asList(MOCK_LINK_RESPONSE);
    public static LinkTrackingResponse MOCK_LINK_TRACKING_RESPONSE = LinkTrackingResponse.builder()
            .topLinks(MOCK_LINK_RESPONSE_LIST)
            .build();


}
