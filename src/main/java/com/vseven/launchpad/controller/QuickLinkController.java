package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.CombinedDTO;
import com.vseven.launchpad.dto.request.LinkOrderDTO;
import com.vseven.launchpad.dto.request.QuickLinkDTO;
import com.vseven.launchpad.dto.request.SectionOrderDTO;
import com.vseven.launchpad.dto.response.*;
import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.entity.User;
import com.vseven.launchpad.entity.Duo;
import com.vseven.launchpad.exception.BadRequestException;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.repository.LinkRepository;
import com.vseven.launchpad.repository.UserQuickLinkRepository;
import com.vseven.launchpad.repository.UserRepository;
import com.vseven.launchpad.entity.*;
import com.vseven.launchpad.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
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
    public ResponseEntity<FullResponse> getQuickLinks(@PathVariable String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        List<UserQuickLink> quickLinksList = userQuickLinkRepository.findByUserUserName(username);

        List<SectionOrder> sectionOrdersList = sectionOrderRepository.findByUserUserName(username);

        List<LinkOrder> linkOrderList = linkOrderRepository.findByUserUserName(username);


//        Map<String, Object> responseMap = new HashMap<>();
//        responseMap.put("userName", username);

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

        List<SectionOrderResponse> sectionOrderContent = sectionOrdersList.stream()
                .map(sectionOrder -> {
                    SectionOrderResponse sectionOrderResponse = SectionOrderResponse.builder()
                            .sectionId(sectionOrder.getSection().getSectionId())
                            .sectionName(sectionOrder.getSection().getSectionName())
                            .sectionOrder(sectionOrder.getSectionOrder())
                            .build();

                    return sectionOrderResponse;
                })
                .toList();


        List<LinkOrderResponse> linkOrderContent = linkOrderList.stream()
                .map(linkOrder -> {
                    LinkOrderResponse linkOrderResponse = LinkOrderResponse.builder()
                            .sectionId(linkOrder.getSection().getSectionId())
                            .linkId(linkOrder.getLink().getLinkId())
                            .linkName(linkOrder.getLink().getLinkName())
                            .linkOrder(linkOrder.getLinkOrder())
                            .build();

                    return linkOrderResponse;
                })
                .toList();

        if (quickLinksList.isEmpty()) {
            FullResponse fullResponse = FullResponse.builder()
                    .userName(username)
                    .quickLink(Collections.emptyList())
                    .linkOrderResponse(linkOrderContent)
                    .sectionOrderResponse(sectionOrderContent)
                    .build();

            return ResponseEntity.ok(fullResponse);
        }

//        responseMap.put("userQuickLinks", quickLinksContent);
//        responseMap.put("sectionOrder", sectionOrderContent);
//        responseMap.put("linkOrder", linkOrderContent);

        FullResponse fullResponse = FullResponse.builder()
                .userName(username)
                .quickLink(quickLinksContent)
                .linkOrderResponse(linkOrderContent)
                .sectionOrderResponse(sectionOrderContent)
                .build();

        return ResponseEntity.ok(fullResponse);
    }


    @PostMapping("/{username}/save")
    public ResponseEntity<?> saveUserQuickLinks(@PathVariable String username, @RequestBody CombinedDTO combinedDTO) {

        User user = userRepository.findByUserName(username);
        Integer userId  = user.getUserId();
        if (user == null) {
            throw new ResourceNotFoundException(ErrorDictionary.NF_002);
        }

        //Used for QuickLink
        QuickLinkDTO quickLinkDTO = combinedDTO.getQuickLinkDTO();
        if (quickLinkDTO != null  ) {
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

        //Used for sectionOrder
        List<SectionOrderDTO> sectionOrderDTOList =  combinedDTO.getSectionOrderDTOList();

        if (sectionOrderDTOList != null ) {

            Integer sectionDtoLength = sectionOrderDTOList.size();
            long sectionLength = sectionRepository.count();

            List<Integer> dtoIds = new ArrayList<>();
            for (SectionOrderDTO sectionOrder : sectionOrderDTOList) {
                dtoIds.add(sectionOrder.getOrder());
            }

            if (sectionLength != sectionDtoLength || hasDuplicateElements(dtoIds) ) {
                throw new ResourceNotFoundException(ErrorDictionary.BR_001);
            }

            for (SectionOrderDTO sectionOrder : sectionOrderDTOList) {
                Optional<Section> sectionOptional = sectionRepository.findById(sectionOrder.getSectionId());
                if (!Objects.equals(sectionOrder.getUserId(), userId)) {
                    throw new BadRequestException(ErrorDictionary.BR_001);
                }
                if (sectionOptional.isPresent()) {
                    sectionOrderRepository.saveSectionOrderNativeQuery(sectionOrder.getUserId(), sectionOrder.getSectionId(), sectionOrder.getOrder());

                } else {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_006);
                }
            }
        }

        //Used for linkOrder
        List<LinkOrderDTO> linkOrderDTOList =  combinedDTO.getLinkOrderDTOList();

        if (linkOrderDTOList != null) {

            Integer linkDtoLength = linkOrderDTOList.size();
//            long linkLength = linkRepository.count();

            Set<Integer> sectionSet = new HashSet<>();



            List<Duo> sectionAndLinkPairList = new ArrayList<>();

            for (LinkOrderDTO linkOrderDTO : linkOrderDTOList) {
                Integer section = linkOrderDTO.getSectionId();
                sectionAndLinkPairList.add(new Duo(section, linkOrderDTO.getLinkOrder()));
                sectionSet.add(section);
            }

            List<Integer> arr = new ArrayList<>(sectionSet);
            Integer totalLinks = linkRepository.countLinksBySectionIds(arr);
//            System.out.println(arr);
//            System.out.println(totalLinks);
//            System.out.println(linkDtoLength);

            if ( totalLinks != linkDtoLength || hasDuplicateLinkIdsAndOrder(sectionAndLinkPairList)) {
                throw new ResourceNotFoundException(ErrorDictionary.BR_001);
            }
            

            for (LinkOrderDTO linkOrderDTO : linkOrderDTOList) {
                Optional<Link> linkOptional = linkRepository.findByLinkId(linkOrderDTO.getLinkId());
                Link linkObject = linkOptional.orElse(null);



                if (linkOptional.isPresent()) {
                    if (!Objects.equals(linkOrderDTO.getUserId(), userId)) {
                        throw new BadRequestException(ErrorDictionary.BR_001);
                        //throw new ResourceNotFoundException(ErrorDictionary.BR_001);
                    }
                    if (!Objects.equals(linkOrderDTO.getSectionId(), linkObject.getSectionId())) {

                        throw new ResourceNotFoundException(ErrorDictionary.NF_008);
                    }
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
                .message("Successful Unbookmark")
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


    private boolean hasDuplicateElements(List<Integer> theList) {
        Set<Integer> seenSet = new HashSet<>();

        for (Integer number : theList) {
            // If the number is already in the set, it's a duplicate
            if (!seenSet.add(number)) {
                return true; // Duplicate found
            }
        }

        // No duplicates found
        return false;
    }

    private boolean hasDuplicateLinkIdsAndOrder(List<Duo> theList) {
        Set<Duo> seenSet = new HashSet<>();

        for (Duo duo : theList) {
            // If the number is already in the set, it's a duplicate
            if (!seenSet.add(duo)) {
                return true; // Duplicate found
            }
        }

        // No duplicates found
        return false;
    }



}
