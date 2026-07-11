package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateTagRequest {
    private String name;
    private String slug;
    private String category;
    private List<String> applicableTo;
}