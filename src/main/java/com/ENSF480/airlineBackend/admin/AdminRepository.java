package com.ENSF480.airlineBackend.admin;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    
    @Query("SELECT a FROM Admin a WHERE a.email = ?1")
    Optional<Admin> findAdminByEmail(String email);
}