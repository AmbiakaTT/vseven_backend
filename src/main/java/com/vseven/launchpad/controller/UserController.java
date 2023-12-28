package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.response.UserResponse;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.repository.UserRepository;
import com.vseven.launchpad.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.vseven.launchpad.entity.UserQuickLink;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/user", produces = APPLICATION_JSON_VALUE)
public class UserController {
    
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> userList = userService.findAll();
        List<UserResponse> userResponseList = userList.stream()
                .map(user -> {
                    UserResponse response = UserResponse.builder()
                            .userId(user.getUserId())
                            .userName(user.getUserName())
                            .email(user.getEmail())
                            .passwordHash(user.getPasswordHash())
                            .enabled(user.getEnabled())
                            .build();
                    return response;
                })
                .toList();
        return ResponseEntity.ok(userResponseList);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id).orElse(null);

        if (user != null) {
            UserResponse response = UserResponse.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .email(user.getEmail())
                    .passwordHash(user.getPasswordHash())
                    .enabled(user.getEnabled())
                    .build();
            return ResponseEntity.ok(response);
        } else {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
            //return ResponseEntity.notFound().build();
        }
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
//        if (userService.existsById(id)) {
//            userService.deleteById(Math.toIntExact(Long.valueOf(id)));
//            return ResponseEntity.ok("User deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

}
