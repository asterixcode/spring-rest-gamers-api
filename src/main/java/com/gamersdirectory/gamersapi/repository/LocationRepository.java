package com.gamersdirectory.gamersapi.repository;

import com.gamersdirectory.gamersapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    Location findLocationByName(String name);
}
