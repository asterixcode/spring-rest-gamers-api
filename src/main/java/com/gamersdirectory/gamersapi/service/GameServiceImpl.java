package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.exception.ApiConflictException;
import com.gamersdirectory.gamersapi.entity.Game;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game save(Game game) {

        gameRepository.findByName(game.getName())
                .ifPresent(a -> { throw new ApiConflictException(
                        String.format("Game [ %s ] already exists.", game.getName()));
                });

        return gameRepository.save(game);
    }

    @Override
    public List<Game> listFive() {
        return null;
    }

    @Override
    public List<Game> listAll() {
        return null;
    }
}
