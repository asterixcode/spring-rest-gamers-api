package com.games.directory.gamersapi.controller;

import com.games.directory.gamersapi.model.Account;
import com.games.directory.gamersapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        return userService.save(account);
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
