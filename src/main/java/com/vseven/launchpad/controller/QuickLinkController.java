package com.vseven.launchpad.controller;

import com.vseven.launchpad.repository.UserQuickLinkRepository;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.vseven.launchpad.entity.UserQuickLink;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/api")
public class QuickLinkController {

    private UserQuickLinkRepository userQuickLinkRepository;

    @Autowired
    public QuickLinkController(UserQuickLinkRepository theuserQuickLinkRepository) {
        userQuickLinkRepository = theuserQuickLinkRepository;
    }
    
    @GetMapping("/{username}/get")
    public ResponseEntity<?> getQuickLinks(@PathVariable String username) {
        List<UserQuickLink> quickLinksList = userQuickLinkRepository.findByUserUserName(username);

        if (quickLinksList == null || quickLinksList.isEmpty()) {
            throw new ResourceNotFoundException("No quick links found for user: " + username);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("username", username);

        List<Map<String, Object>> quickLinksContent = quickLinksList.stream()
                .map(quickLink -> {
                    Map<String, Object> quickLinkMap = new HashMap<>();
                    quickLinkMap.put("id", quickLink.getLink().getLinkId());
                    quickLinkMap.put("LinkName", quickLink.getLink().getLinkName());
                    quickLinkMap.put("Url", quickLink.getLink().getUrl());
                    // Add more properties as needed
                    return quickLinkMap;
                })
                .toList();

        responseMap.put("quickLinks", quickLinksContent);

        return ResponseEntity.ok(responseMap);
    }

    @PostMapping
    public ResponseEntity<String> deleteQuickLinks(@PathVariable String username, List<Integer> deleteList) {
        
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetToHomePage(@PathVariable String username) {
        return ResponseEntity.ok("{\"message\": \"Reset successful\"}");
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveQuickLinks(@PathVariable String username) {
        return ResponseEntity.ok("{\"message\": \"Reset successful\"}");
    }

}
