package com.vseven.launchpad.exception.response;

import com.azure.core.annotation.Get;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionResponse {
    private ErrorDictionary errorId;
    private Integer status;
    private String message;
}
