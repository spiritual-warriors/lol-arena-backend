package com.spiritualwarriors.lol_arena.service;

import com.spiritualwarriors.lol_arena.domain.dto.ItemDto;
import com.spiritualwarriors.lol_arena.domain.entity.Item;
import com.spiritualwarriors.lol_arena.domain.entity.Tag;
import com.spiritualwarriors.lol_arena.domain.repository.ItemRepository;
import com.spiritualwarriors.lol_arena.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final TagService tagService;

    public ItemService(ItemRepository itemRepository, TagService tagService) {
        this.itemRepository = itemRepository;
        this.tagService = tagService;
    }

    public List<ItemDto> getAllItems() {
        return itemRepository.findAllWithTags().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> getAllItemsAdmin() {
        return itemRepository.findAllWithTagsAdmin().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ItemDto> getItemsByTag(String tagSlug) {
        return itemRepository.findByTagSlugWithTags(tagSlug).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public ItemDto setItemEnabled(Long itemId, boolean enabled) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
        item.setEnabled(enabled);
        Item saved = itemRepository.save(item);
        return mapToDto(saved);
    }

    @Transactional
    public ItemDto addTagsToItem(Long itemId, Set<Long> tagIds) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
        for (Long tagId : tagIds) {
            Tag tag = tagService.getTagEntity(tagId);
            item.getTags().add(tag);
        }
        Item saved = itemRepository.save(item);
        return mapToDto(saved);
    }

    @Transactional
    public ItemDto removeTagFromItem(Long itemId, Long tagId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found with id: " + itemId));
        Tag tag = tagService.getTagEntity(tagId);
        item.getTags().remove(tag);
        Item saved = itemRepository.save(item);
        return mapToDto(saved);
    }

    ItemDto mapToDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setImage(item.getImage());
        dto.setEnabled(item.getEnabled());
        dto.setTags(item.getTags().stream()
                .map(tagService::mapToDto)
                .collect(Collectors.toSet()));
        return dto;
    }
}