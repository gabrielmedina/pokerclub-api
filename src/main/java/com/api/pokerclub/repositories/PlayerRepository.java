package com.api.pokerclub.repositories;

import com.api.pokerclub.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerModel, UUID> {
    boolean existsByName(String name);
}
