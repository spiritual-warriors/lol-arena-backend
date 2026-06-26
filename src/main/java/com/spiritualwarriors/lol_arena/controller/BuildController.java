package com.spiritualwarriors.lol_arena.controller;

import com.spiritualwarriors.lol_arena.domain.dto.BuildDto;
import com.spiritualwarriors.lol_arena.domain.dto.CreateBuildRequest;
import com.spiritualwarriors.lol_arena.service.BuildService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/builds")
public class BuildController {

    private final BuildService buildService;

    public BuildController(BuildService buildService) {
        this.buildService = buildService;
    }

    @GetMapping
    public ResponseEntity<List<BuildDto>> getAllBuilds() {
        return ResponseEntity.ok(buildService.getAllBuilds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildDto> getBuildById(@PathVariable Long id) {
        return ResponseEntity.ok(buildService.getBuildById(id));
    }

    @PostMapping
    public ResponseEntity<BuildDto> createBuild(@RequestBody CreateBuildRequest request) {
        BuildDto created = buildService.createBuild(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

