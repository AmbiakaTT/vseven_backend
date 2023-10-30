package com.vseven.launchpad.controller;


import com.azure.core.annotation.Get;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("/api")
public class DefaultController {

    @GetMapping("/")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello, World!");
    }

    @GetMapping("/login")
    public ResponseEntity<String> test_dog() {
        return ResponseEntity.ok("Hello, am dog!");
    }

}