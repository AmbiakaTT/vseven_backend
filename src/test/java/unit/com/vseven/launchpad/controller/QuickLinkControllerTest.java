package unit.com.vseven.launchpad.controller;

import com.vseven.launchpad.controller.QuickLinkController;
import com.vseven.launchpad.dto.response.FullResponse;
import com.vseven.launchpad.dto.response.MessageResponse;
import com.vseven.launchpad.dto.response.SectionOrderResponse;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.*;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unit.com.vseven.launchpad.utils.MockDataUtils;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuickLinkControllerTest {

    @InjectMocks
    private QuickLinkController controller;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserQuickLinkRepository userQuickLinkRepository;
    @Mock
    private SectionOrderRepository sectionOrderRepository;
    @Mock
    private LinkOrderRepository linkOrderRepository;
    @Mock
    private LinkRepository linkRepository;
    @Mock
    private SectionRepository sectionRepository;


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getAPI_validRequest_returnSuccess() throws Exception{
        when(userRepository.findByUserName(any())).thenReturn(MockDataUtils.MOCK_USER_1);
        when(userQuickLinkRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_USER_QUICK_LINK_LIST);
        when(sectionOrderRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_SECTION_ORDER_LIST);
        when(linkOrderRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_LINK_ORDER_LIST);

        String userName = "test user";
        ResponseEntity<FullResponse> response = controller.getQuickLinks(userName);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_FULL_RESPONSE.toString()));
    }

    @Test
    public void saveAPI_validRequest_returnSuccess() throws Exception{
        when(userRepository.findByUserName(any())).thenReturn(MockDataUtils.MOCK_USER_1);
        when(linkRepository.findByLinkId(any())).thenReturn(MockDataUtils.MOCK_LINK_OPTIONAL);
        when(userQuickLinkRepository.findByUserNameAndLinkId(any(), any())).thenReturn(MockDataUtils.MOCK_USER_QUICK_LINK_OPTIONAL);
        when(sectionRepository.findById(any())).thenReturn(MockDataUtils.MOCK_SECTION_OPTIONAL);
        when(sectionRepository.count()).thenReturn(1L);
        when(linkRepository.countLinksBySectionIds(any())).thenReturn(1);
        //when(userQuickLinkRepository.save(MockDataUtils.MOCK_USER_QUICK_LINK)).thenReturn(MockDataUtils.MOCK_USER_QUICK_LINK);
        //when(linkOrderRepository.findById(any())).thenReturn(Optional.of(MockDataUtils.MOCK_LINK_ORDER_ENTITY));
        //when(sectionOrderRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_SECTION_ORDER_LIST);
        //when(linkOrderRepository.findByLinkId(any())).thenReturn(MockDataUtils.MOCK_LINK_ORDER_LIST);


        String userName = "test user";
        ResponseEntity<MessageResponse> response = controller.saveUserQuickLinks(userName, MockDataUtils.MOCK_COMBINED_DTO);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_SAVE_MESSAGE_RESPONSE.toString()));

    }

    @Test
    public void unbookmarkAPI_validRequest_returnSuccess() throws Exception {
        when(userRepository.findByUserName(any())).thenReturn(MockDataUtils.MOCK_USER_1);
        when(linkRepository.findByLinkId(any())).thenReturn(MockDataUtils.MOCK_LINK_OPTIONAL);
        when(userQuickLinkRepository.findByUserNameAndLinkId(any(), any())).thenReturn(MockDataUtils.MOCK_USER_QUICK_LINK_OPTIONAL);

        String userName = "test user";
        ResponseEntity<MessageResponse> response = controller.deleteQuickLinks(userName, MockDataUtils.MOCK_QUICK_LINK_DTO);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_UNBOOKMARK_MESSAGE_RESPONSE.toString()));

    }

    @Test
    public void resetAPI_validRequest_returnSuccess() throws Exception {
        when(userRepository.findByUserName(any())).thenReturn(MockDataUtils.MOCK_USER_1);

        String userName = "test user";
        ResponseEntity<?> response = controller.resetToHomePage(userName);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_RESET_MESSAGE_RESPONSE.toString()));

    }



}
