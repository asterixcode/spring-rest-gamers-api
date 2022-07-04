package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.dto.InterestDTO;
import com.gamersdirectory.gamersapi.entity.*;
import com.gamersdirectory.gamersapi.exception.ApiRequestException;
import com.gamersdirectory.gamersapi.repository.AccountRepository;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import com.gamersdirectory.gamersapi.repository.LevelRepository;
import com.gamersdirectory.gamersapi.repository.LocationRepository;
import com.gamersdirectory.gamersapi.validation.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_ID_NOT_FOUND = "Account Id %s not found";
    private final AccountRepository accountRepository;
    private final LocationRepository locationRepository;
    private final GameRepository gameRepository;
    private final LevelRepository levelRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, LocationRepository locationRepository, GameRepository gameRepository, LevelRepository levelRepository) {
        this.accountRepository = accountRepository;
        this.locationRepository = locationRepository;
        this.gameRepository = gameRepository;
        this.levelRepository = levelRepository;
    }

    @Override
    public AccountDTO save(AccountForm accountForm) {
        Location findLocation = findLocationOrFail(accountForm.getLocation());

        Account account = new Account();

        account.setName(accountForm.getName());
        account.setNickname(accountForm.getNickname());
        account.setLocation(findLocation);

        if (!accountForm.getInterests().isEmpty()) {
            Map<Game, Level> gamesAndLevel = mapGamesAndLevelsOrFail(accountForm);
            List<Interest> interestList = fromMapToList(gamesAndLevel);
            account.setInterests(interestList);
        }

        return new AccountDTO(accountRepository.save(account));
    }

    private Location findLocationOrFail(String locationName) {
        Location location = locationRepository.findLocationByName(locationName);
        if (location == null) {
            throw new ApiRequestException(
                    String.format("Location [ %s ] does not exist.", locationName)
            );
        }
        return location;
    }

    private Map<Game, Level> mapGamesAndLevelsOrFail(AccountForm accountForm) {
        List<InterestDTO> interests = accountForm.getInterests()
                .stream()
                .toList();

        Map<Game, Level> map = new HashMap<>();

        for (InterestDTO dto : interests) {
            Game findGame = gameRepository.findByName(dto.getGame())
                    .orElseThrow(() -> new ApiRequestException(
                            String.format("Game [ %s ] does not exist.", dto.getGame())
                    ));

            Level findLevel = levelRepository.findByName(dto.getLevel())
                    .orElseThrow(() -> new ApiRequestException(
                            String.format("Level [ %s ] does not exist.", dto.getLevel())
                    ));

            map.put(findGame, findLevel);
        }
        return map;
    }

    private List<Interest> fromMapToList(Map<Game, Level> map) {
        List<Interest> interestList = new ArrayList<>();
        Map<Game, Level> gamesList = new HashMap<>();

        for (Map.Entry<Game, Level> maps : map.entrySet()) {
            Interest interest = new Interest();
            interest.setGame(maps.getKey());
            interest.setLevel(maps.getValue());
            interestList.add(interest);
        }

        return interestList;
    }

    @Override
    public AccountDTO findById(Long accountId) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(ACCOUNT_ID_NOT_FOUND, accountId)
                ));
        return new AccountDTO(account);
    }
}
