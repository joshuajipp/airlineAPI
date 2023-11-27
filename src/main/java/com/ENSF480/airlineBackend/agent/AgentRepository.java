package com.ENSF480.airlineBackend.agent;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    
    @Query("SELECT a FROM Agent a WHERE a.email = ?1")
    Optional<Agent> findAgentByEmail(String email);
}
