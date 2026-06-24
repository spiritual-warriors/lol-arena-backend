package com.spiritualwarriors.lol_arena.domain.repository;

import com.spiritualwarriors.lol_arena.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT DISTINCT i FROM Item i LEFT JOIN FETCH i.tags")
    List<Item> findAllWithTags();

    @Query("""
            SELECT DISTINCT i FROM Item i
            JOIN FETCH i.tags t
            WHERE t.slug = :slug
            """)
    List<Item> findByTagSlugWithTags(@Param("slug") String slug);
}
