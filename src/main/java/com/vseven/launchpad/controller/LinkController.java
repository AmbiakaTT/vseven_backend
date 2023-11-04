package com.vseven.launchpad.controller;


import com.vseven.launchpad.entity.Link;
import com.vseven.launchpad.repository.LinkRepository;
import com.vseven.launchpad.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LinkController {

    public LinkService linkService;

    @Autowired
    public LinkController(LinkService thelinkService)    {
        linkService = thelinkService;
    }

    @GetMapping("/links")
    public List<Link> getAllLinks() {
        return linkService.findAll();
    }

    @GetMapping("/links/{id}")
    public ResponseEntity<Link> getLinkById(@PathVariable Long id) {
        Optional<Link> link = linkService.findById(id);

        if (link.isPresent()) {
            return ResponseEntity.ok(link.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/links/{id}")
    public ResponseEntity<String> deleteLink(@PathVariable Long id) {
        // Check if the link with the given ID exists
        if (linkService.existsById(id)) {
            linkService.deleteById(id);
            return ResponseEntity.ok("Link deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/save")
    public void saveLinks(@RequestBody Link link) {
        linkService.save(link);
    }
}