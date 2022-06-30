package com.games.directory.gamersapi.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // many interest have the same one game
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    private Level level;

}
