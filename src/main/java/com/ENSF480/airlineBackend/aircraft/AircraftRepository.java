package com.ENSF480.airlineBackend.aircraft;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    @Query("SELECT a FROM Aircraft a WHERE a.name = ?1")
    Optional<Aircraft> findAircraftByName(String name);

}