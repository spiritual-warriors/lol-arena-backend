package com.spiritualwarriors.lol_arena.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class CreateBuildRequest {
    private String championId;
    private String title;
    private String description;
    private String effectivity;
    private List<Long> itemIds;
    private List<Long> augmentIds;
}