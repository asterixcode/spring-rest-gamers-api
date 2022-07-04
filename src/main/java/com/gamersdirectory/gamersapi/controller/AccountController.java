package com.gamersdirectory.gamersapi.controller;

import com.gamersdirectory.gamersapi.dto.AccountDTO;
import com.gamersdirectory.gamersapi.validation.AccountForm;
import com.gamersdirectory.gamersapi.entity.Account;
import com.gamersdirectory.gamersapi.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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
    @Operation(summary = "Create new account", responses = {
            @ApiResponse(description = "Account created successfully.",
                    responseCode = "201",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountDTO.class)))
    })
    public ResponseEntity<AccountDTO> signup(@RequestBody @Valid AccountForm accountForm, UriComponentsBuilder uriBuilder) {

        AccountDTO accountDTO = accountService.save(accountForm);

        URI uri = uriBuilder.path("api/v1/account/{id}").buildAndExpand(accountDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(accountDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get account by Id", responses = {
            @ApiResponse(description = "Account found.",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class))),
            @ApiResponse(description = "Account not found.", responseCode = "409", content = @Content)
    })
    public AccountDTO getById(@PathVariable Long id) {
        return accountService.findById(id);
    }



}
