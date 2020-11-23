package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team,Long> {
    Team findByName(String teamName);
}
