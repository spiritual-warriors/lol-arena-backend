package com.spiritualwarriors.lol_arena.controller;

import com.spiritualwarriors.lol_arena.domain.dto.ChampionDto;
import com.spiritualwarriors.lol_arena.domain.dto.BuildDto;
import com.spiritualwarriors.lol_arena.service.ChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/champions")
public class ChampionController {

    private final ChampionService championService;

    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @GetMapping
    public ResponseEntity<List<ChampionDto>> getAllChampions() {
        return ResponseEntity.ok(championService.getAllChampions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampionDto> getChampionById(@PathVariable String id) {
        return ResponseEntity.ok(championService.getChampionById(id));
    }

    @GetMapping("/{id}/builds")
    public ResponseEntity<List<BuildDto>> getChampionBuilds(@PathVariable String id) {
        return ResponseEntity.ok(championService.getChampionBuilds(id));
    }
}