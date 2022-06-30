package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.model.Account;

public interface AccountService {
    Account save(Account account);
    Account findById(Long accountId);
}
