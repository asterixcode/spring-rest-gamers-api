package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.exception.ApiRequestException;
import com.gamersdirectory.gamersapi.model.Account;
import com.gamersdirectory.gamersapi.model.Location;
import com.gamersdirectory.gamersapi.repository.AccountRepository;
import com.gamersdirectory.gamersapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_ID_NOT_FOUND = "Account Id %s not found";
    private final AccountRepository accountRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, LocationRepository locationRepository) {
        this.accountRepository = accountRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Account save(Account account) {
        String locationName = account.getLocation().getName();

        Location location = locationRepository.findLocationsByName(locationName);

        if (location == null) {
            throw new ApiRequestException(
                    String.format("Location [ %s ] does not exist.", locationName)
            );
        }
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(ACCOUNT_ID_NOT_FOUND, accountId)
                ));
    }
}
