package com.spiritualwarriors.lol_arena.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "builds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Build {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String effectivity; // e.g., S-Tier, A-Tier, Meta

    @ManyToMany(mappedBy = "builds")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Champion> champions = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "build_items",
        joinColumns = @JoinColumn(name = "build_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Item> items = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "build_augments",
        joinColumns = @JoinColumn(name = "build_id"),
        inverseJoinColumns = @JoinColumn(name = "augment_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Augment> augments = new HashSet<>();
}
