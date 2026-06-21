package com.spiritualwarriors.lol_arena.domain.repository;

import com.spiritualwarriors.lol_arena.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findBySlug(String slug);
    boolean existsBySlug(String slug);
}