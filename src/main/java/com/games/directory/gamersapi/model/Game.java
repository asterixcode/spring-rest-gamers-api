package com.games.directory.gamersapi.domain.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

}
