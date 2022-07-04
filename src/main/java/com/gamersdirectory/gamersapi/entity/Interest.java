package com.gamersdirectory.gamersapi.entity;

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
    private Long id;

    @ManyToOne // many interest have the same one game
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne // many interest have the same one level
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "level_id")
    private Level level;
}
