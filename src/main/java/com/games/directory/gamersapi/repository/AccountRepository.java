package com.games.directory.gamersapi.repository;

import com.games.directory.gamersapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT u FROM Account u WHERE u.nickname = :nickname")
    Optional<Account> findUserByNickname(String nickname);

}
