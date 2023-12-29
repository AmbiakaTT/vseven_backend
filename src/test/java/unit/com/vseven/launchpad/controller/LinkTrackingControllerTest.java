package unit.com.vseven.launchpad.controller;

import com.vseven.launchpad.controller.LinkTrackingController;
import com.vseven.launchpad.controller.QuickLinkController;
import com.vseven.launchpad.dto.response.LinkTrackingResponse;
import com.vseven.launchpad.dto.response.MessageResponse;
import com.vseven.launchpad.repository.LinkClickRepository;
import com.vseven.launchpad.repository.LinkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unit.com.vseven.launchpad.utils.MockDataUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LinkTrackingControllerTest {

    @InjectMocks
    private LinkTrackingController controller;
    @Mock
    private LinkClickRepository linkClickRepository;
    @Mock
    private LinkRepository linkRepository;

    @Test
    public void getTopLinks_validRequest_returnSuccess() throws Exception {
        when(linkClickRepository.findTop5ByOrderByNumOfClicksDesc()).thenReturn(MockDataUtils.MOCK_LINK_CLICK_LIST);

        ResponseEntity<LinkTrackingResponse> response = controller.getTopLinks();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_LINK_TRACKING_RESPONSE.toString()));

    }

    @Test
    public void updateLinks_validRequest_returnSuccess() throws Exception {
        when(linkRepository.findByLinkId(any())).thenReturn(MockDataUtils.MOCK_LINK_OPTIONAL);

        ResponseEntity<MessageResponse> response = controller.updateLinks(MockDataUtils.MOCK_LINK_TRACKING_DTO_LIST);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_UPDATE_LINK_MESSAGE_RESPONSE.toString()));
    }

}
