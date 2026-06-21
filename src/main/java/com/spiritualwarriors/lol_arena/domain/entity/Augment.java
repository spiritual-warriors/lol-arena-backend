package com.spiritualwarriors.lol_arena.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "augments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Augment {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String tier;

    private String image;

    @ManyToMany(mappedBy = "augments")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Build> builds = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "augment_tags",
        joinColumns = @JoinColumn(name = "augment_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();
}