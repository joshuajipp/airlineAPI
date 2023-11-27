package com.ENSF480.airlineBackend.reg_user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ENSF480.airlineBackend.user.User;

public interface RegisteredUserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM RegisteredUser u WHERE u.email = ?1")
    Optional<RegisteredUser> searchByEmail(String email);
}
