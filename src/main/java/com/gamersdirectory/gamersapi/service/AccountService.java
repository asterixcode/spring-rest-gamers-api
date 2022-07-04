package com.gamersdirectory.gamersapi.service;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.validation.AccountForm;

public interface AccountService {
    AccountDTO save(AccountForm accountForm);
    AccountDTO findById(Long accountId);
}
