package com.gamersdirectory.gamersapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    //@ManyToOne
    //@Cascade(CascadeType.ALL) // many interest have the same one game
    //@JoinColumn(name = "game_id")
    private String game;

    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Level level;
}
