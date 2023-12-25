package com.vseven.launchpad.exception;

import com.vseven.launchpad.exception.response.ErrorDictionary;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BadRequestException extends RuntimeException {
    static final long serialVersionUID = 1L;
    private ErrorDictionary errorId;
    private HttpStatus status;
    private String message;

    public BadRequestException(ErrorDictionary errorId) {
        super(errorId.getErrorMessage());
        this.errorId = errorId;
        this.status = HttpStatus.BAD_REQUEST;
        this.message = errorId.getErrorMessage();
    }
}
