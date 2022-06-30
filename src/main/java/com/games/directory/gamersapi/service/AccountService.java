package com.games.directory.gamersapi.service;

import com.games.directory.gamersapi.model.Account;

public interface AccountService {
    Account save(Account account);

    Account findById(Long accountId);

    void findByNickname(String nickname);

}
