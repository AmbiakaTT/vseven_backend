package unit.com.vseven.launchpad.service;

import com.vseven.launchpad.dto.response.UserResponse;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.UserRepository;
import com.vseven.launchpad.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import unit.com.vseven.launchpad.utils.MockDataUtils;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void findAll_invokesRespository() {
        List<UserResponse> expected = MockDataUtils.MOCK_USER_RESPONSE_LIST;

        when(userRepository.findAll()).thenReturn(MockDataUtils.MOCK_USER_LIST);

        List<User> actual = userService.findAll();
        assertThat(actual.size(), is(expected.size()));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void findById_invokesRepository() {
        Optional<UserResponse> expected = MockDataUtils.MOCK_USER_RESPONSE_OPTIONAL;
        when(userRepository.findById(any())).thenReturn(MockDataUtils.MOCK_USER_OPTIONAL);

        Optional<User> actual = userService.findById(MockDataUtils.MOCK_USER_ID);
        //assertThat(actual.getClass(), is(expected.getClass()));
        verify(userRepository, times(1)).findById(Long.valueOf(MockDataUtils.MOCK_USER_ID));

    }
}
