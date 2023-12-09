package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.repository.LinkRepository;
import com.vseven.launchpad.repository.UserQuickLinkRepository;
import com.vseven.launchpad.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.vseven.launchpad.entity.UserQuickLink;

import java.util.*;

import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api")
public class QuickLinkController {

    private final UserQuickLinkRepository userQuickLinkRepository;

    private final UserRepository userRepository;

    private final LinkRepository linkRepository;


    @GetMapping("/{username}/get")
    public ResponseEntity<?> getQuickLinks(@PathVariable String username) {
        List<UserQuickLink> quickLinksList = userQuickLinkRepository.findByUserUserName(username);

        if (quickLinksList == null || quickLinksList.isEmpty()) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_001);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("userName", username);

        List<Map<String, Object>> quickLinksContent = quickLinksList.stream()
                .map(quickLink -> {
                    Map<String, Object> quickLinkMap = new HashMap<>();
                    quickLinkMap.put("linkId", quickLink.getLink().getLinkId());
                    quickLinkMap.put("linkName", quickLink.getLink().getLinkName());
                    quickLinkMap.put("url", quickLink.getLink().getUrl());
                    // Add more properties as needed
                    return quickLinkMap;
                })
                .toList();

        responseMap.put("userQuickLinks", quickLinksContent);

        return ResponseEntity.ok(responseMap);
    }


    @PostMapping("/{username}/save")
    public ResponseEntity<?> saveUserQuickLinks(@PathVariable String username, @RequestBody QuickLinkDTO quickLinkDTO) {
        List<Integer> linkIds = quickLinkDTO.getLinksId();

        User user = userRepository.findByUserName(username);
        if (user == null) {
            // Handle the case where the user with the specified username is not found
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        for (Integer linkId : linkIds) {
            Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(linkId));
            Optional<UserQuickLink> quickLinkOptional = userQuickLinkRepository.findByUserNameAndLinkId(username, linkId);
            //System.out.println(quickLinkOptional);
            if (linkOptional.isPresent() && !quickLinkOptional.isPresent()) {
                Link link = linkOptional.get();

                UserQuickLink userQuickLink = new UserQuickLink();
                userQuickLink.setUser(user);
                userQuickLink.setLink(link);
                userQuickLinkRepository.save(userQuickLink);
            } else {
                // Handle the case where the link with the specified ID is not found
                if (!linkOptional.isPresent()) {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_003);
                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_004);
                }
            }
        }
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Successfully Saved");

        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/{username}/unbookmark")
    public ResponseEntity<?> deleteQuickLinks(@PathVariable String username, @RequestBody QuickLinkDTO quickLinkDTO) {
        List<Integer> linkIds = quickLinkDTO.getLinksId();

        User user = userRepository.findByUserName(username);
        if (user == null) {
            // Handle the case where the user with the specified username is not found
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }
        //userQuickLinkRepository.deleteByUserNameAndLinkIdsNativeQuery(username, linkIds);
        for (Integer id : linkIds) {
            Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(id));
            Optional<UserQuickLink> quickLinkOptional = userQuickLinkRepository.findByUserNameAndLinkId(username, id);
            if (linkOptional.isPresent() && quickLinkOptional.isPresent()) {
                userQuickLinkRepository.deleteByUserNameAndLinkId(username, id);
            } else {
                // Handle the case where the link with the specified ID is not found
                if (!linkOptional.isPresent()) {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_005);
                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_003);
                }
            }
        }
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Successfully Unbookmark");

        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/{username}/reset")
    public ResponseEntity<String> resetToHomePage(@PathVariable String username) {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            // Handle the case where the user with the specified username is not found
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }
        try {
            userQuickLinkRepository.resetUserQuickLinkNativeQuery(username);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during reset");

        }
        return ResponseEntity.ok("{\"message\": \"Reset successful\"}");
    }


}