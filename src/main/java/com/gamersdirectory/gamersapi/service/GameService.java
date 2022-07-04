package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.entity.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {

    List<Game> listAll();

}
