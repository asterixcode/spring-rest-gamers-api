package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.model.Account;
import com.gamersdirectory.gamersapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.name = :name")
    Optional<Account> findLocationByName(String name);
}
