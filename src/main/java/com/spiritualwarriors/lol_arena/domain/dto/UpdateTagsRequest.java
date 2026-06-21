package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateTagsRequest {
    private List<Long> tagIds;
}