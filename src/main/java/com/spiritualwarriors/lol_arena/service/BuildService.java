package com.spiritualwarriors.lol_arena.service;

import com.spiritualwarriors.lol_arena.domain.dto.BuildDto;
import com.spiritualwarriors.lol_arena.domain.dto.ChampionDto;
import com.spiritualwarriors.lol_arena.domain.dto.CreateBuildRequest;
import com.spiritualwarriors.lol_arena.domain.entity.Augment;
import com.spiritualwarriors.lol_arena.domain.entity.Build;
import com.spiritualwarriors.lol_arena.domain.entity.Champion;
import com.spiritualwarriors.lol_arena.domain.entity.Item;
import com.spiritualwarriors.lol_arena.domain.repository.AugmentRepository;
import com.spiritualwarriors.lol_arena.domain.repository.BuildRepository;
import com.spiritualwarriors.lol_arena.domain.repository.ChampionRepository;
import com.spiritualwarriors.lol_arena.domain.repository.ItemRepository;
import com.spiritualwarriors.lol_arena.exception.ResourceNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildService {

    private final BuildRepository buildRepository;
    private final ChampionRepository championRepository;
    private final ItemRepository itemRepository;
    private final AugmentRepository augmentRepository;
    private final ItemService itemService;
    private final AugmentService augmentService;

    public BuildService(BuildRepository buildRepository,
                        ChampionRepository championRepository,
                        ItemRepository itemRepository,
                        AugmentRepository augmentRepository,
                        ItemService itemService,
                        AugmentService augmentService) {
        this.buildRepository = buildRepository;
        this.championRepository = championRepository;
        this.itemRepository = itemRepository;
        this.augmentRepository = augmentRepository;
        this.itemService = itemService;
        this.augmentService = augmentService;
    }

    @Transactional(readOnly = true)
    public BuildDto getBuildById(Long id) {
        Build build = buildRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Build not found with id: " + id));
        return mapToDto(build);
    }

    @Transactional(readOnly = true)
    @Cacheable("builds")
    public List<BuildDto> getAllBuilds() {
        return buildRepository.findAllBy(Sort.by(Sort.Direction.DESC, "createdAt")).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @CacheEvict(value = "builds", allEntries = true)
    public BuildDto createBuild(CreateBuildRequest request) {
        Build build = Build.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .effectivity(request.getEffectivity())
                .build();

        if (request.getItemIds() != null && !request.getItemIds().isEmpty()) {
            List<Item> items = itemRepository.findAllById(request.getItemIds());
            build.setItems(new HashSet<>(items));
        }

        if (request.getAugmentIds() != null && !request.getAugmentIds().isEmpty()) {
            List<Augment> augments = augmentRepository.findAllById(request.getAugmentIds());
            build.setAugments(new HashSet<>(augments));
        }

        Build savedBuild = buildRepository.save(build);

        if (request.getChampionIds() != null && !request.getChampionIds().isEmpty()) {
            List<Champion> champions = championRepository.findAllById(request.getChampionIds());
            for (Champion champion : champions) {
                champion.getBuilds().add(savedBuild);
                savedBuild.getChampions().add(champion);
                championRepository.save(champion);
            }
        }

        return mapToDto(savedBuild);
    }

    public BuildDto mapToDto(Build build) {
        BuildDto dto = new BuildDto();
        dto.setId(build.getId());
        dto.setTitle(build.getTitle());
        dto.setDescription(build.getDescription());
        dto.setEffectivity(build.getEffectivity());
        dto.setCreatedAt(build.getCreatedAt());

        dto.setChampions(build.getChampions().stream()
                .map(this::mapChampionToDto)
                .collect(Collectors.toSet()));

        dto.setItems(build.getItems().stream().map(itemService::mapToDto).collect(Collectors.toCollection(LinkedHashSet::new)));
        dto.setAugments(build.getAugments().stream()
                .sorted(AugmentService.BY_TIER)
                .map(augmentService::mapToDto)
                .collect(Collectors.toCollection(LinkedHashSet::new)));

        return dto;
    }

    private ChampionDto mapChampionToDto(Champion champion) {
        ChampionDto dto = new ChampionDto();
        dto.setId(champion.getId());
        dto.setName(champion.getName());
        dto.setImage(champion.getImage());
        dto.setEnabled(champion.getEnabled());
        dto.setTags(champion.getTags());
        return dto;
    }
}