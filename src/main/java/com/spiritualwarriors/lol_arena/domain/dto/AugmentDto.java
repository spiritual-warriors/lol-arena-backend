package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;

import java.util.Set;

@Data
public class AugmentDto {
    private Long id;
    private String name;
    private String description;
    private String tier;
    private String notes;
    private String image;
    private Boolean enabled;
    private Set<TagDto> tags;
}