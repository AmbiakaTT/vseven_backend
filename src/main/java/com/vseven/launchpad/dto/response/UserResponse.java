package com.vseven.launchpad.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@SuperBuilder
//@NoArgsConstructor
//@AllArgsConstructor
@Getter
//@Setter
@ToString
public class UserResponse {
    private Integer userId;

    private String userName;

    private String email;

    private String passwordHash;

    private Integer enabled;


}
