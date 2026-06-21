package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ChampionDto {
    private String id;
    private String name;
    private String image;
    private List<String> tags;
}