package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.repository.LinkRepository;
import com.vseven.launchpad.repository.UserQuickLinkRepository;
import com.vseven.launchpad.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.vseven.launchpad.entity.UserQuickLink;

import java.util.*;

import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("/api")
public class QuickLinkController {

    private UserQuickLinkRepository userQuickLinkRepository;
    
    private UserRepository userRepository;

    private LinkRepository linkRepository;

    @Autowired
    public QuickLinkController(UserQuickLinkRepository theuserQuickLinkRepository, UserRepository theuserRepository, LinkRepository theLinkRepository) {
        userQuickLinkRepository = theuserQuickLinkRepository;
        userRepository = theuserRepository;
        linkRepository = theLinkRepository;
    }

    @GetMapping("/{username}/get")
    public ResponseEntity<?> getQuickLinks(@PathVariable String username) {
        List<UserQuickLink> quickLinksList = userQuickLinkRepository.findByUserUserName(username);

        if (quickLinksList == null || quickLinksList.isEmpty()) {
            throw new ResourceNotFoundException("No quick links found for user: " + username);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("userName", username);

        List<Map<String, Object>> quickLinksContent = quickLinksList.stream()
                .map(quickLink -> {
                    Map<String, Object> quickLinkMap = new HashMap<>();
                    quickLinkMap.put("linkId", quickLink.getLink().getLinkId());
                    quickLinkMap.put("LinkName", quickLink.getLink().getLinkName());
                    quickLinkMap.put("Url", quickLink.getLink().getUrl());
                    // Add more properties as needed
                    return quickLinkMap;
                })
                .toList();

        responseMap.put("userQuickLinks", quickLinksContent);

        return ResponseEntity.ok(responseMap);
    }


    @PostMapping("/{username}/save")
    public ResponseEntity<String> saveUserQuickLinks(@PathVariable String username, @RequestBody QuickLinkDTO quickLinkDTO) {
        List<Integer> linkIds = quickLinkDTO.getLinksId();

        User user = userRepository.findByUserName(username);

        if (user == null) {
            // Handle the case where the user with the specified username is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        for (Integer linkId : linkIds) {
            Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(linkId));

            if (linkOptional.isPresent()) {
                Link link = linkOptional.get();

                UserQuickLink userQuickLink = new UserQuickLink();
                userQuickLink.setUser(user);
                userQuickLink.setLink(link);
                userQuickLinkRepository.save(userQuickLink);
            } else {
                // Handle the case where the link with the specified ID is not found
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link not found for ID: " + linkId);
            }
        }

        return ResponseEntity.ok("Operation completed successfully");
    }

    @PostMapping("/{username}/unbookmark")
    public ResponseEntity<String> deleteQuickLinks(@PathVariable String username, @RequestBody QuickLinkDTO quickLinkDTO) {
        List<Integer> linkIds = quickLinkDTO.getLinksId();
        try {
            userQuickLinkRepository.deleteByUserNameAndLinkIdsNativeQuery(username, linkIds);
            return ResponseEntity.ok("Deletion completed successfully");
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during deletion");
        }
    }
    @PostMapping("/reset")
    public ResponseEntity<String> resetToHomePage(@PathVariable String username) {
        return ResponseEntity.ok("{\"message\": \"Reset successful\"}");
    }


}
