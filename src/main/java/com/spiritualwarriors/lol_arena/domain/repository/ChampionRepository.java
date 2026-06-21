package com.spiritualwarriors.lol_arena.domain.repository;

import com.spiritualwarriors.lol_arena.domain.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, String> {
}