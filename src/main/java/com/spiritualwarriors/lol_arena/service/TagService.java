package com.spiritualwarriors.lol_arena.service;

import com.spiritualwarriors.lol_arena.domain.dto.TagDto;
import com.spiritualwarriors.lol_arena.domain.entity.Tag;
import com.spiritualwarriors.lol_arena.domain.repository.TagRepository;
import com.spiritualwarriors.lol_arena.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagDto> getAllTags(String applicableTo) {
        if (applicableTo == null || applicableTo.isEmpty()) {
            return tagRepository.findAll().stream()
                    .map(this::mapToDto)
                    .collect(Collectors.toList());
        }
        return tagRepository.findAllByApplicableToContaining(applicableTo).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public TagDto createTag(TagDto request) {
        if (tagRepository.existsBySlug(request.getSlug())) {
            throw new RuntimeException("Tag with slug '" + request.getSlug() + "' already exists");
        }
        Tag tag = Tag.builder()
                .name(request.getName())
                .slug(request.getSlug())
                .category(request.getCategory() != null ? request.getCategory() : "custom")
                .applicableTo(request.getApplicableTo() != null ? request.getApplicableTo() : List.of("ITEM", "AUGMENT"))
                .build();
        Tag saved = tagRepository.save(tag);
        return mapToDto(saved);
    }

    public Tag getTagEntity(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));
    }

    TagDto mapToDto(Tag tag) {
        TagDto dto = new TagDto();
        dto.setId(tag.getId());
        dto.setName(tag.getName());
        dto.setSlug(tag.getSlug());
        dto.setCategory(tag.getCategory());
        dto.setApplicableTo(tag.getApplicableTo());
        return dto;
    }
}