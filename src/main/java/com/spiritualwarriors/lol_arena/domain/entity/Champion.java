package com.spiritualwarriors.lol_arena.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "champions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Champion {
    
    @Id
    private String id;
    
    @Column(nullable = false)
    private String name;
    
    private String image;

    @JdbcTypeCode(SqlTypes.ARRAY)
    @Column(columnDefinition = "varchar[]")
    private List<String> tags;

    @ManyToMany
    @JoinTable(
        name = "champion_builds",
        joinColumns = @JoinColumn(name = "champion_id"),
        inverseJoinColumns = @JoinColumn(name = "build_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Build> builds = new HashSet<>();
}