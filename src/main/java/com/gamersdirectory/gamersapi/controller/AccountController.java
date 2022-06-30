package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.model.Account;
import com.gamersdirectory.gamersapi.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/account")
@Tag(name="Account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new account", responses = {
            @ApiResponse(description = "Account created successfully.",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)))
    })
    public Account create(@RequestBody Account account) {
        return accountService.save(account);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by Id", responses = {
            @ApiResponse(description = "Account found.",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))),
            @ApiResponse(description = "Account not found", responseCode = "409", content = @Content)
    })
    public Account getById(@PathVariable Long id) {
        return accountService.findById(id);
    }
}
