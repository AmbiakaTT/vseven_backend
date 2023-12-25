package unit.com.vseven.launchpad.controller;

import com.vseven.launchpad.controller.QuickLinkController;
import com.vseven.launchpad.dto.response.SectionOrderResponse;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.LinkOrderRepository;
import com.vseven.launchpad.repository.SectionOrderRepository;
import com.vseven.launchpad.repository.UserQuickLinkRepository;
import com.vseven.launchpad.repository.UserRepository;
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

import static org.hamcrest.MatcherAssert.assertThat;
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


    @BeforeEach
    public void setUp() {

    }

    @Test
    public void getAPI_validRequest_returnSuccess() throws Exception{
        when(userRepository.findByUserName(any())).thenReturn(MockDataUtils.MOCK_USER);
        when(userQuickLinkRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_USER_QUICK_LINK_LIST);
        when(sectionOrderRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_SECTION_ORDER_LIST);
        when(linkOrderRepository.findByUserUserName(any())).thenReturn(MockDataUtils.MOCK_LINK_ORDER_LIST);

        String userName = "test user";
        ResponseEntity<?> response = controller.getQuickLinks(userName);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_FULL_RESPONSE.toString()));
    }

}
