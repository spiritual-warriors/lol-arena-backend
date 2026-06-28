package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;
import java.util.Set;

@Data
public class BuildDto {
    private Long id;
    private Set<ChampionDto> champions;
    private String title;
    private String description;
    private String effectivity;
    private Set<ItemDto> items;
    private Set<AugmentDto> augments;
}