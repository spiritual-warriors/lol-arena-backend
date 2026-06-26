package com.spiritualwarriors.lol_arena.service;

import com.spiritualwarriors.lol_arena.domain.dto.BuildDto;
import com.spiritualwarriors.lol_arena.domain.dto.CreateBuildRequest;
import com.spiritualwarriors.lol_arena.domain.dto.ItemDto;
import com.spiritualwarriors.lol_arena.domain.dto.AugmentDto;
import com.spiritualwarriors.lol_arena.domain.entity.Augment;
import com.spiritualwarriors.lol_arena.domain.entity.Build;
import com.spiritualwarriors.lol_arena.domain.entity.Champion;
import com.spiritualwarriors.lol_arena.domain.entity.Item;
import com.spiritualwarriors.lol_arena.domain.repository.AugmentRepository;
import com.spiritualwarriors.lol_arena.domain.repository.BuildRepository;
import com.spiritualwarriors.lol_arena.domain.repository.ChampionRepository;
import com.spiritualwarriors.lol_arena.domain.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
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

    public BuildDto getBuildById(Long id) {
        Build build = buildRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Build not found with id: " + id));
        return mapToDto(build);
    }

    @Transactional
    public List<BuildDto> getAllBuilds() {
        return buildRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
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

        if (request.getChampionId() != null) {
            Champion champion = championRepository.findById(request.getChampionId())
                    .orElseThrow(() -> new RuntimeException("Champion not found with id: " + request.getChampionId()));
            champion.getBuilds().add(savedBuild);
            championRepository.save(champion);
            savedBuild.getChampions().add(champion);
        }

        return mapToDto(savedBuild);
    }

    public BuildDto mapToDto(Build build) {
        BuildDto dto = new BuildDto();
        dto.setId(build.getId());
        dto.setTitle(build.getTitle());
        dto.setDescription(build.getDescription());
        dto.setEffectivity(build.getEffectivity());

        if (build.getChampions() != null && !build.getChampions().isEmpty()) {
            Champion champ = build.getChampions().iterator().next();
            dto.setChampionId(champ.getId());
            dto.setChampionName(champ.getName());
        }
        
        dto.setItems(build.getItems().stream().map(itemService::mapToDto).collect(Collectors.toSet()));
        dto.setAugments(build.getAugments().stream().map(augmentService::mapToDto).collect(Collectors.toSet()));
        
        return dto;
    }
}