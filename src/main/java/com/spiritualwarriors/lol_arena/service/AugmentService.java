package com.spiritualwarriors.lol_arena.service;

import com.spiritualwarriors.lol_arena.domain.dto.AugmentDto;
import com.spiritualwarriors.lol_arena.domain.entity.Augment;
import com.spiritualwarriors.lol_arena.domain.entity.Tag;
import com.spiritualwarriors.lol_arena.domain.repository.AugmentRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AugmentService {

    private final AugmentRepository augmentRepository;
    private final TagService tagService;

    public AugmentService(AugmentRepository augmentRepository, TagService tagService) {
        this.augmentRepository = augmentRepository;
        this.tagService = tagService;
    }

    @Cacheable(value = "augments", key = "'all'")
    public List<AugmentDto> getAllAugments() {
        return augmentRepository.findAllWithTags().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "augments", key = "'tag:' + #tagSlug")
    public List<AugmentDto> getAugmentsByTag(String tagSlug) {
        return augmentRepository.findByTagSlugWithTags(tagSlug).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AugmentDto addTagsToAugment(Long augmentId, Set<Long> tagIds) {
        Augment augment = augmentRepository.findById(augmentId)
                .orElseThrow(() -> new RuntimeException("Augment not found with id: " + augmentId));
        for (Long tagId : tagIds) {
            Tag tag = tagService.getTagEntity(tagId);
            augment.getTags().add(tag);
        }
        Augment saved = augmentRepository.save(augment);
        return mapToDto(saved);
    }

    @Transactional
    public AugmentDto removeTagFromAugment(Long augmentId, Long tagId) {
        Augment augment = augmentRepository.findById(augmentId)
                .orElseThrow(() -> new RuntimeException("Augment not found with id: " + augmentId));
        Tag tag = tagService.getTagEntity(tagId);
        augment.getTags().remove(tag);
        Augment saved = augmentRepository.save(augment);
        return mapToDto(saved);
    }

    AugmentDto mapToDto(Augment augment) {
        AugmentDto dto = new AugmentDto();
        dto.setId(augment.getId());
        dto.setName(augment.getName());
        dto.setDescription(augment.getDescription());
        dto.setTier(augment.getTier());
        dto.setImage(augment.getImage());
        dto.setEnabled(augment.getEnabled());
        dto.setTags(augment.getTags().stream()
                .map(tagService::mapToDto)
                .collect(Collectors.toSet()));
        return dto;
    }
}