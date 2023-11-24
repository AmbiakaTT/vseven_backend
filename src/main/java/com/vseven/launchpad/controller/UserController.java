package com.vseven.launchpad.controller;

import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.UserRepository;
import com.vseven.launchpad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.vseven.launchpad.entity.UserQuickLink;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService theuserService) {
        this.userService = theuserService;
    }

    @GetMapping("/")
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.findById(id).orElse(null);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (userService.existsById(id)) {
            userService.deleteById(Math.toIntExact(Long.valueOf(id)));
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
