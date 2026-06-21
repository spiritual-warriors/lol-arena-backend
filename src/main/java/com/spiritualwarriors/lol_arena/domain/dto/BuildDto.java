package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;
import java.util.Set;

@Data
public class BuildDto {
    private Long id;
    private String championId;
    private String championName;
    private String description;
    private String effectivity;
    private Set<ItemDto> items;
    private Set<AugmentDto> augments;
}