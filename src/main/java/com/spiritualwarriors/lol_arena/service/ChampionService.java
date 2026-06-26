package com.spiritualwarriors.lol_arena.service;

import com.spiritualwarriors.lol_arena.domain.dto.ChampionDto;
import com.spiritualwarriors.lol_arena.domain.dto.BuildDto;
import com.spiritualwarriors.lol_arena.domain.entity.Champion;
import com.spiritualwarriors.lol_arena.domain.repository.ChampionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionService {

    private final ChampionRepository championRepository;
    private final BuildService buildService;

    public ChampionService(ChampionRepository championRepository, BuildService buildService) {
        this.championRepository = championRepository;
        this.buildService = buildService;
    }

    public List<ChampionDto> getAllChampions() {
        return championRepository.findAll().stream()
                .map(this::mapToDto)
                .sorted(Comparator.comparing(ChampionDto::getName))
                .collect(Collectors.toList());
    }

    public ChampionDto getChampionById(String id) {
        Champion champion = championRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champion not found with id: " + id));
        return mapToDto(champion);
    }

    public List<BuildDto> getChampionBuilds(String id) {
        Champion champion = championRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Champion not found with id: " + id));
        return champion.getBuilds().stream()
                .map(buildService::mapToDto)
                .collect(Collectors.toList());
    }

    private ChampionDto mapToDto(Champion champion) {
        ChampionDto dto = new ChampionDto();
        dto.setId(champion.getId());
        dto.setName(champion.getName());
        dto.setImage(champion.getImage());
        dto.setTags(champion.getTags());
        return dto;
    }
}