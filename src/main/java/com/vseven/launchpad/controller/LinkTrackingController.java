package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.request.LinkTrackingDTO;
import com.vseven.launchpad.dto.response.LinkResponse;
import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.entity.LinkClick;
import com.vseven.launchpad.exception.ResourceNotFoundException;
import com.vseven.launchpad.exception.response.ErrorDictionary;
import com.vseven.launchpad.repository.LinkClickRepository;
import com.vseven.launchpad.repository.LinkRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class LinkTrackingController {

    private final LinkClickRepository linkClickRepository;

    private final LinkRepository linkRepository;

    @GetMapping("/top-links")
    public ResponseEntity<?> getTopLinks() {

        List<LinkClick> topLinks  = linkClickRepository.findTop5ByOrderByNumOfClicksDesc();

        List<LinkResponse> topLinksContent = topLinks.stream()
                .map(topLink -> {
                    LinkResponse linkResponse = LinkResponse.builder()
                            .linkId(topLink.getLink().getLinkId())
                            .linkName(topLink.getLink().getLinkName())
                            .url(topLink.getLink().getUrl())
                            .build();

                    return linkResponse;
                })
                .toList();

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("topLinks", topLinksContent);

        return ResponseEntity.ok(responseMap);

    }

    @PostMapping("/top-links")
    public ResponseEntity<?> updateLinks(@RequestBody @NotNull List<LinkTrackingDTO> linkTrackingDTO) {

        for (LinkTrackingDTO trackingLink  : linkTrackingDTO ) {
            Optional<Link> linkOptional = linkRepository.findById(Long.valueOf(trackingLink.getLinkId()));
            if (linkOptional.isPresent()) {
                linkClickRepository.saveLinkClickNativeQuery(trackingLink.getLinkId(), trackingLink.getNumOfClicks());
            }
            else {
                if (!linkOptional.isPresent()) {
                    throw new ResourceNotFoundException(ErrorDictionary.NF_005);
                }
            }
        }
        return  ResponseEntity.ok("Link Tracking success");

    }

}
