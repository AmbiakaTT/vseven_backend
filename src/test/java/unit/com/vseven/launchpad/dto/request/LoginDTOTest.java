package unit.com.vseven.launchpad.dto.request;

import com.vseven.launchpad.dto.request.LoginDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class LoginDTOTest {
    @Test
    void testGettersAndSetters() {
        LoginDTO loginDTO = new LoginDTO();

        loginDTO.setUsername("testUser");
        loginDTO.setPassword("testPassword");

        assertEquals("testUser", loginDTO.getUsername());
        assertEquals("testPassword", loginDTO.getPassword());
    }

    @Test
    void testEqualsAndHashCode() {
        LoginDTO loginDTO1 = LoginDTO.builder()
                .username("testUser")
                .password("testPassword")
                .build();

        LoginDTO loginDTO2 = LoginDTO.builder()
                .username("testUser")
                .password("testPassword")
                .build();

        LoginDTO differentLoginDTO = LoginDTO.builder()
                .username("anotherUser")
                .password("anotherPassword")
                .build();

        // Test equality
        assertEquals(loginDTO1, loginDTO2);
        assertEquals(loginDTO1.hashCode(), loginDTO2.hashCode());

        // Test inequality
        assertNotEquals(loginDTO1, differentLoginDTO);
        assertNotEquals(loginDTO1.hashCode(), differentLoginDTO.hashCode());
    }

    @Test
    void testToString() {
        LoginDTO loginDTO = LoginDTO.builder()
                .username("testUser")
                .password("testPassword")
                .build();

        String expectedToString = "LoginDTO(username=testUser, password=testPassword)";

        assertEquals(expectedToString, loginDTO.toString());
    }

}
