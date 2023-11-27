package com.ENSF480.airlineBackend.reg_user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long> {


    @Query("SELECT a FROM RegisteredUser a WHERE a.email = ?1")
    Optional<RegisteredUser> findRegisteredUserByEmail(String email);
} 
