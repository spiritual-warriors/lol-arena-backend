package com.spiritualwarriors.lol_arena.controller;

import com.spiritualwarriors.lol_arena.domain.dto.AugmentDto;
import com.spiritualwarriors.lol_arena.domain.dto.UpdateTagsRequest;
import com.spiritualwarriors.lol_arena.service.AugmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/augments")
public class AugmentController {

    private final AugmentService augmentService;

    public AugmentController(AugmentService augmentService) {
        this.augmentService = augmentService;
    }

    @GetMapping
    public ResponseEntity<List<AugmentDto>> getAugments(@RequestParam(required = false) String tag) {
        if (tag != null && !tag.isEmpty()) {
            return ResponseEntity.ok(augmentService.getAugmentsByTag(tag));
        }
        return ResponseEntity.ok(augmentService.getAllAugments());
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<AugmentDto> addTagsToAugment(@PathVariable Long id, @RequestBody UpdateTagsRequest request) {
        return ResponseEntity.ok(augmentService.addTagsToAugment(id, Set.copyOf(request.getTagIds())));
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<AugmentDto> removeTagFromAugment(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok(augmentService.removeTagFromAugment(id, tagId));
    }
}