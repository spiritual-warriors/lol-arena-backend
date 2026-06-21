package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;

@Data
public class CreateTagRequest {
    private String name;
    private String slug;
}