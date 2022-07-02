package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.exception.ApiRequestException;
import com.gamersdirectory.gamersapi.model.Account;
import com.gamersdirectory.gamersapi.model.Interest;
import com.gamersdirectory.gamersapi.model.Location;
import com.gamersdirectory.gamersapi.repository.AccountRepository;
import com.gamersdirectory.gamersapi.repository.GameRepository;
import com.gamersdirectory.gamersapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_ID_NOT_FOUND = "Account Id %s not found";
    private final AccountRepository accountRepository;
    private final LocationRepository locationRepository;
    private final GameRepository gameRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, LocationRepository locationRepository, GameRepository gameRepository) {
        this.accountRepository = accountRepository;
        this.locationRepository = locationRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Account save(Account account) {
        Location location = findLocationOrFail(account.getLocation().getName());

        List<Interest> interests = findGamesOrFail(account);

        Account toAdd = new Account();
        List<Interest> interestList = new ArrayList<>();

        toAdd.setName(account.getName());
        toAdd.setNickname(account.getNickname());
        toAdd.setLocation(location);
        for (Interest value : interests) {
            Interest interest = new Interest();
            interest.setGame(value.getGame());
            interest.setLevel(value.getLevel());
            interestList.add(interest);
        }
        toAdd.setInterests(interestList);

        return accountRepository.save(toAdd);
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

    private List<Interest> findGamesOrFail(Account account) {
        List<Interest> interests = account.getInterests()
                .stream()
                .toList();

        List<String> listOfGames = interests.stream()
                .map(Interest::getGame)
                .toList();

        for (String game : listOfGames) {
            gameRepository.findByName(game)
                    .orElseThrow(() -> new ApiRequestException(
                            String.format("Game [ %s ] does not exist.", game)
                    ));
        }
        return interests;
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(ACCOUNT_ID_NOT_FOUND, accountId)
                ));
    }
}
