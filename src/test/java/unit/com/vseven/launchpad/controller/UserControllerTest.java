package unit.com.vseven.launchpad.controller;

import com.vseven.launchpad.controller.UserController;
import com.vseven.launchpad.dto.response.UserResponse;
import com.vseven.launchpad.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import unit.com.vseven.launchpad.utils.MockDataUtils;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;
    @Mock
    private UserService userService;

    @Test
    public void getAllUsers_validRequest_returnSuccess() throws Exception {
        when(userService.findAll()).thenReturn(MockDataUtils.MOCK_USER_LIST);

        ResponseEntity<List<UserResponse>> response = controller.getAllUsers();

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_USER_RESPONSE_LIST.toString()));
    }

    @Test
    public void getUserById_validRequest_returnSuccess() throws Exception {
        when(userService.findById(any())).thenReturn(MockDataUtils.MOCK_USER_OPTIONAL);

        ResponseEntity<UserResponse> response = controller.getUserById(MockDataUtils.MOCK_USER_ID);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody().toString(), is(MockDataUtils.MOCK_USER_1_RESPONSE.toString()));

    }
}
