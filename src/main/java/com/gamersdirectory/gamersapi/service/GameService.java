package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.entity.Game;

import java.util.List;

public interface GameService {

    Game save(Game game);

    List<Game> listFive();

    List<Game> listAll();
}
