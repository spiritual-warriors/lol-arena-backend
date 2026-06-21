package com.spiritualwarriors.lol_arena.domain.repository;

import com.spiritualwarriors.lol_arena.domain.entity.Augment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AugmentRepository extends JpaRepository<Augment, Long> {
    @Query("SELECT a FROM Augment a JOIN a.tags t WHERE t.slug = :slug")
    List<Augment> findByTagSlug(@Param("slug") String slug);
}
