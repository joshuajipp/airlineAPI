package com.ENSF480.airlineBackend.staff;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long>{
    
    @Query("SELECT a FROM Staff a WHERE a.email = ?1")
    Optional<Staff> findStaffByEmail(String email);
}
