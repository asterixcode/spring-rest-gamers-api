package com.games.directory.gamersapi.service;

import com.games.directory.gamersapi.model.Account;
import com.games.directory.gamersapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AccountServiceImpl implements AccountService {

    private static final String ACCOUNT_ID_NOT_FOUND = "Account Id %d not found";
    private static final String NICKNAME_NOTFOUND = "Nickname %d not found";
    public AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Long accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format(ACCOUNT_ID_NOT_FOUND, accountId)
                ));
    }

    @Override
    public void findByNickname(String nickname) {
        accountRepository.findUserByNickname(nickname)
                .ifPresent(u -> { throw new EntityNotFoundException(
                        String.format(NICKNAME_NOTFOUND, nickname));
                });

    }
}
