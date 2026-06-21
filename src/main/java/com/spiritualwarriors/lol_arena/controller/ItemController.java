package com.spiritualwarriors.lol_arena.controller;

import com.spiritualwarriors.lol_arena.domain.dto.ItemDto;
import com.spiritualwarriors.lol_arena.domain.dto.UpdateTagsRequest;
import com.spiritualwarriors.lol_arena.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(@RequestParam(required = false) String tag) {
        if (tag != null && !tag.isEmpty()) {
            return ResponseEntity.ok(itemService.getItemsByTag(tag));
        }
        return ResponseEntity.ok(itemService.getAllItems());
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<ItemDto> addTagsToItem(@PathVariable Long id, @RequestBody UpdateTagsRequest request) {
        return ResponseEntity.ok(itemService.addTagsToItem(id, Set.copyOf(request.getTagIds())));
    }

    @DeleteMapping("/{id}/tags/{tagId}")
    public ResponseEntity<ItemDto> removeTagFromItem(@PathVariable Long id, @PathVariable Long tagId) {
        return ResponseEntity.ok(itemService.removeTagFromItem(id, tagId));
    }
}