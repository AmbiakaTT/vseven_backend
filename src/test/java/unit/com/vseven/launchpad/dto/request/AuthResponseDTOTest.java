package unit.com.vseven.launchpad.dto.request;
import com.vseven.launchpad.dto.request.AuthResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
public class AuthResponseDTOTest {
    @Test
    void testGettersAndSetters() {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO("testAccessToken");

        assertEquals("testAccessToken", authResponseDTO.getAccessToken());
        assertEquals("Bearer ", authResponseDTO.getTokenType());

        // Test setter
        authResponseDTO.setAccessToken("newAccessToken");
        assertEquals("newAccessToken", authResponseDTO.getAccessToken());
    }

    @Test
    void testEqualsAndHashCode() {
        AuthResponseDTO authResponseDTO1 = AuthResponseDTO.builder()
                .accessToken("testAccessToken")
                .build();

        AuthResponseDTO authResponseDTO2 = AuthResponseDTO.builder()
                .accessToken("testAccessToken")
                .build();

        AuthResponseDTO differentAuthResponseDTO = AuthResponseDTO.builder()
                .accessToken("differentAccessToken")
                .build();

        // Test equality
        assertEquals(authResponseDTO1, authResponseDTO2);
        assertEquals(authResponseDTO1.hashCode(), authResponseDTO2.hashCode());

        // Test inequality
        assertNotEquals(authResponseDTO1, differentAuthResponseDTO);
        assertNotEquals(authResponseDTO1.hashCode(), differentAuthResponseDTO.hashCode());
    }

    @Test
    void testToString() {
        AuthResponseDTO authResponseDTO = AuthResponseDTO.builder()
                .accessToken("testAccessToken")
                .tokenType("Bearer ")
                .build();

        String expectedToString = "AuthResponseDTO(accessToken=testAccessToken, tokenType=Bearer )";

        assertEquals(expectedToString, authResponseDTO.toString());
    }
}
