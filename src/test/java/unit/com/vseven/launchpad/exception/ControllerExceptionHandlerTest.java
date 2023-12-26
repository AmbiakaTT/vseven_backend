package unit.com.vseven.launchpad.exception;

import com.vseven.launchpad.exception.BadRequestException;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.controller.ControllerExceptionHandler;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.exception.response.ExceptionResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@ExtendWith(MockitoExtension.class)
public class ControllerExceptionHandlerTest {

    @InjectMocks
    private ControllerExceptionHandler handler;

    @Test
    public void handleResourceNotFoundException_shouldSuccess() {
        ResourceNotFoundException exception = new ResourceNotFoundException(ErrorDictionary.NF_002);
        ResponseEntity<ExceptionResponse> actual = handler.handleResourceNotFoundException(exception);

        ExceptionResponse response = ExceptionResponse.builder()
                .errorId(ErrorDictionary.NF_002)
                .status(HttpStatus.NOT_FOUND.value())
                .message(ErrorDictionary.NF_002.getErrorMessage())
                .build();

        ResponseEntity<ExceptionResponse> expected = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        assertThat(actual.getStatusCode(), is(expected.getStatusCode()));
        assertThat(actual.getBody().getStatus(), is(expected.getBody().getStatus()));
        assertThat(actual.getBody().getErrorId(), is(expected.getBody().getErrorId()));
        assertThat(actual.getBody().getMessage(), is(expected.getBody().getMessage()));
    }

    @Test
    public void handleBadRequestException_shouldSuccess() {
        BadRequestException exception = new BadRequestException(ErrorDictionary.BR_001);
        ResponseEntity<ExceptionResponse> actual = handler.handleBadRequestException(exception);

        ExceptionResponse response = ExceptionResponse.builder()
                .errorId(ErrorDictionary.BR_001)
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ErrorDictionary.BR_001.getErrorMessage())
                .build();

        ResponseEntity<ExceptionResponse> expected = new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        assertThat(actual.getStatusCode(), is(expected.getStatusCode()));
        assertThat(actual.getBody().getStatus(), is(expected.getBody().getStatus()));
        assertThat(actual.getBody().getErrorId(), is(expected.getBody().getErrorId()));
        assertThat(actual.getBody().getMessage(), is(expected.getBody().getMessage()));
    }

}
