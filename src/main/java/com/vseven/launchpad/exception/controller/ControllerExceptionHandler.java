package com.vseven.launchpad.exception.controller;

import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ExceptionResponse;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ControllerAdvice
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            ResourceNotFoundException exception
    ) {
        ExceptionResponse response = ExceptionResponse.builder()
                .errorId(exception.getErrorId())
                .status(exception.getStatus().value())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response, exception.getStatus());
    }
}
