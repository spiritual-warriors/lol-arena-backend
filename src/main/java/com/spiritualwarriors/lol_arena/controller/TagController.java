package com.spiritualwarriors.lol_arena.controller;

import com.spiritualwarriors.lol_arena.domain.dto.CreateTagRequest;
import com.spiritualwarriors.lol_arena.domain.dto.TagDto;
import com.spiritualwarriors.lol_arena.service.TagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @PostMapping
    public ResponseEntity<TagDto> createTag(@RequestBody CreateTagRequest request) {
        TagDto tag = tagService.createTag(new TagDto() {{ 
            setName(request.getName()); 
            setSlug(request.getSlug()); 
        }});
        return ResponseEntity.ok(tag);
    }
}