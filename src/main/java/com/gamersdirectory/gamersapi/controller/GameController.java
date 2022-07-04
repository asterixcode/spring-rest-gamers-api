package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.entity.Game;
import com.gamersdirectory.gamersapi.service.GameService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/game")
@Tag(name="Game")
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<Game> create(@RequestBody Game game) {
        return new ResponseEntity<>(gameService.save(game), HttpStatus.CREATED);
    }

    @GetMapping("/fetch/5")
    public ResponseEntity<List<Game>> getFiveGames() {
        return ResponseEntity.ok(gameService.listFive());
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Game>> getAllGames() {
        return ResponseEntity.ok(gameService.listAll());
    }
}
