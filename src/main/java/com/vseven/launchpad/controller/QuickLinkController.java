package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.entity.*;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final SectionOrderRepository sectionOrderRepository;

    private final LinkOrderRepository linkOrderRepository;
    
    @GetMapping("/{username}/get")
    public ResponseEntity<?> getQuickLinks(@PathVariable String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        List<UserQuickLink> quickLinksList = userQuickLinkRepository.findByUserUserName(username);

        List<SectionOrder> sectionOrdersList = sectionOrderRepository.findByUserUserName(username);

        List<LinkOrder> linkOrderList = linkOrderRepository.findByUserUserName(username);
        
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

        List<Map<String, Object>> sectionOrderContent = sectionOrdersList.stream()
                .map(sectionOrder -> {
                    Map<String, Object> sectionOrderMap = new HashMap<>();
                    sectionOrderMap.put("sectionId", sectionOrder.getSection().getSectionId());
                    sectionOrderMap.put("sectionName", sectionOrder.getSection().getSectionName());
                    sectionOrderMap.put("order", sectionOrder.getSectionOrder());
                    // Add more properties as needed
                    return sectionOrderMap;
                })
                .toList();


        List<Map<String, Object>> linkOrderContent = linkOrderList.stream()
                .map(linkOrder -> {
                    Map<String, Object> linkOrderMap = new HashMap<>();
                    linkOrderMap.put("sectionId", linkOrder.getSection().getSectionId());
                    linkOrderMap.put("sectionName", linkOrder.getSection().getSectionName());
                    linkOrderMap.put("order", linkOrder.getLinkOrder());
                    // Add more properties as needed
                    return linkOrderMap;
                })
                .toList();

        responseMap.put("userQuickLinks", quickLinksContent);
        responseMap.put("sectionOrder", sectionOrderContent);
        responseMap.put("linkOrder", linkOrderContent);


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
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Successfully Saved");

        return ResponseEntity.ok(responseMap);
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
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Successfully Unbookmark");

        return ResponseEntity.ok(responseMap);
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

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Successfully Reset");

        return ResponseEntity.ok(responseMap);
    }


}