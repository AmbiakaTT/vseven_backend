package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.CombinedDTO;
import com.vseven.launchpad.dto.request.LinkOrderDTO;
import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.dto.request.SectionOrderDTO;
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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class QuickLinkController {

    private final UserQuickLinkRepository userQuickLinkRepository;

    private final UserRepository userRepository;

    private final LinkRepository linkRepository;

    private final SectionOrderRepository sectionOrderRepository;

    private final LinkOrderRepository linkOrderRepository;

    private final SectionRepository sectionRepository;

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
    public ResponseEntity<?> saveUserQuickLinks(@PathVariable String username, @RequestBody CombinedDTO combinedDTO) {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        QuickLinkDTO quickLinkDTO = combinedDTO.getQuickLinkDTO();

        if (quickLinkDTO != null) {
            List<Integer> linkIds = quickLinkDTO.getLinksId();

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
        }

        List<SectionOrderDTO> sectionOrderDTOList =  combinedDTO.getSectionOrderDTOList();
        if (sectionOrderDTOList != null) {
            System.out.println("Section order not null");
            for (SectionOrderDTO sectionOrder : sectionOrderDTOList) {
                Optional<Section> sectionOptional = sectionRepository.findById(sectionOrder.getSectionId());
                if (sectionOptional.isPresent()) {
                    sectionOrderRepository.saveSectionOrderNativeQuery(sectionOrder.getUserId(), sectionOrder.getSectionId(), sectionOrder.getOrder());

                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_006);
                }
            }
        } else {
            // Debugging purposes
            System.out.println("Section order null");
        }

        List<LinkOrderDTO> linkOrderDTOList =  combinedDTO.getLinkOrderDTOList();

        if (linkOrderDTOList != null) {
            // Debugging purposes
            // System.out.println("Hello I am not null");
            for (LinkOrderDTO linkOrderDTO : linkOrderDTOList) {
                Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(linkOrderDTO.getLinkId()));
                if (linkOptional.isPresent()) {
                    linkOrderRepository.saveLinkOrderNativeQuery(linkOrderDTO.getUserId(), linkOrderDTO.getSectionId(), linkOrderDTO.getLinkOrder(), linkOrderDTO.getLinkId());

                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_005);
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
