package com.vseven.launchpad.controller;

import com.vseven.launchpad.dto.response.LinkResponse;
import com.vseven.launchpad.entity.LinkClick;
import com.vseven.launchpad.repository.LinkClickRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class LinkTrackingController {

    private final LinkClickRepository linkClickRepository;

    @GetMapping("/topLinks")
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

}
