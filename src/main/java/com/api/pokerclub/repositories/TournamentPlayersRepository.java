package com.api.pokerclub.repositories;

import com.api.pokerclub.models.TournamentPlayersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TournamentPlayersRepository extends JpaRepository<TournamentPlayersModel, UUID> {
    List<TournamentPlayersModel> findByPlayerId(UUID id);
}
