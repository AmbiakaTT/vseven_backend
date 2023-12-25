package unit.com.vseven.launchpad.utils;

import com.vseven.launchpad.dto.response.FullResponse;
import com.vseven.launchpad.dto.response.LinkOrderResponse;
import com.vseven.launchpad.dto.response.LinkResponse;
import com.vseven.launchpad.dto.response.SectionOrderResponse;
import com.vseven.launchpad.entity.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class MockDataUtils {
    public static Integer MOCK_LINK_ID = 1;
    public static Integer MOCK_SECTION_ID = 1;
    public static String MOCK_URL = "url";
    public static String MOCK_LINK_NAME = "link name";
    public static String MOCK_SECTION_NAME = "section name";
    public static Integer MOCK_LINK_ORDER = 1;
    public static Integer MOCK_SECTION_ORDER = 1;
    public static String MOCK_USER_NAME = "test user";
    public static User MOCK_USER = User.builder()
            .userName(MOCK_USER_NAME)
            .build();
    public static Link MOCK_LINK = Link.builder()
            .linkId(MOCK_LINK_ID)
            .url(MOCK_URL)
            .linkName(MOCK_LINK_NAME)
            .sectionId(MOCK_SECTION_ID)
            .build();
    public static UserQuickLink MOCK_USER_QUICK_LINK = UserQuickLink.builder()
            .link(MOCK_LINK)
            .build();
    public static List<UserQuickLink> MOCK_USER_QUICK_LINK_LIST = Arrays.asList(MOCK_USER_QUICK_LINK);
    public static Section MOCK_SECTION = Section.builder()
            .sectionId(MOCK_SECTION_ID)
            .sectionName(MOCK_SECTION_NAME)
            .build();
    public static SectionOrder MOCK_SECTION_ORDER_ENTITY = SectionOrder.builder()
            .section(MOCK_SECTION)
            .sectionOrder(MOCK_SECTION_ORDER)
            .user(MOCK_USER)

            .build();
    public static List<SectionOrder> MOCK_SECTION_ORDER_LIST = Arrays.asList(MOCK_SECTION_ORDER_ENTITY);
    public static LinkOrder MOCK_LINK_ORDER_ENTITY = LinkOrder.builder()
            .link(MOCK_LINK)
            .linkOrder(MOCK_LINK_ORDER)
            .section(MOCK_SECTION)
            .build();
    public static List<LinkOrder> MOCK_LINK_ORDER_LIST = Arrays.asList(MOCK_LINK_ORDER_ENTITY);
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


}
