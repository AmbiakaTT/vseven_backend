package com.vseven.launchpad.controller;


import com.azure.core.annotation.Get;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DefaultController {

    @GetMapping("/")
    public String home() {
        return "Welcome to VSeven Launchpad";
    }
}
