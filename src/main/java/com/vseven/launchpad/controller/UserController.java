package com.vseven.launchpad.controller;

import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.UserRepository;
import com.vseven.launchpad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService theuserService) {
        this.userService = theuserService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        User user = userService.findById(Long.valueOf(id)).orElse(null);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        if (userService.existsById(Long.valueOf(id)) ) {
            userService.deleteById(Math.toIntExact(Long.valueOf(id)));
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        if (userService.existsById(Long.valueOf(id))) {
            updatedUser.setId(Long.valueOf(id)); // Ensure the ID in the updatedUser object matches the path variable
            User savedUser = userService.save(updatedUser);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */


}
