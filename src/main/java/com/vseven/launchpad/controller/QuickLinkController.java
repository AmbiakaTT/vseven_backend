package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.dto.response.LinkResponse;
import com.vseven.launchpad.dto.response.MessageResponse;
import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.repository.LinkClickRepository;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class QuickLinkController {

    private final UserQuickLinkRepository userQuickLinkRepository;

    private final UserRepository userRepository;

    private final LinkRepository linkRepository;

    private final LinkClickRepository linkClickRepository;

    
    @GetMapping("/{username}/get")
    public ResponseEntity<?> getQuickLinks(@PathVariable String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        List<UserQuickLink> quickLinksList = userQuickLinkRepository.findByUserUserName(username);

        if (quickLinksList == null || quickLinksList.isEmpty()) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_001);
        }

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("userName", username);

        List<LinkResponse> quickLinksContent = quickLinksList.stream()
                .map(quickLink -> {
                    LinkResponse linkResponse = LinkResponse.builder()
                            .linkId(quickLink.getLink().getLinkId())
                            .linkName(quickLink.getLink().getLinkName())
                            .url(quickLink.getLink().getUrl())
                            .build();

                    return linkResponse;
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
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        for (Integer linkId : linkIds) {
            Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(linkId));
            Optional<UserQuickLink> quickLinkOptional = userQuickLinkRepository.findByUserNameAndLinkId(username, linkId);

            if (linkOptional.isPresent() && !quickLinkOptional.isPresent()) {
                Link link = linkOptional.get();

                UserQuickLink userQuickLink = UserQuickLink.builder()
                        .user(user)
                        .link(link)
                        .build();

                userQuickLinkRepository.save(userQuickLink);
            } else {
                if (!linkOptional.isPresent()) {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_005);
                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_004);
                }
            }
        }
        MessageResponse response = MessageResponse.builder()
                .message("Successfully Saved")
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{username}/unbookmark")
    public ResponseEntity<?> deleteQuickLinks(@PathVariable String username, @RequestBody QuickLinkDTO quickLinkDTO) {
        List<Integer> linkIds = quickLinkDTO.getLinksId();

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        for (Integer id : linkIds) {
            Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(id));
            Optional<UserQuickLink> quickLinkOptional = userQuickLinkRepository.findByUserNameAndLinkId(username, id);
            if (linkOptional.isPresent() && quickLinkOptional.isPresent()) {
                userQuickLinkRepository.deleteByUserNameAndLinkId(username, id);
            } else {
                if (!linkOptional.isPresent()) {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_005);
                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_003);
                }
            }
        }
        MessageResponse response = MessageResponse.builder()
                .message("Successfully Unbookmark")
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{username}/reset")
    public ResponseEntity<?> resetToHomePage(@PathVariable String username) {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }
        try {
            userQuickLinkRepository.resetUserQuickLinkNativeQuery(username);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during reset");

        }
        MessageResponse response = MessageResponse.builder()
                .message("Successfully Reset")
                .build();


        return ResponseEntity.ok(response);
    }


}