package com.api.pokerclub.repositories;

import com.api.pokerclub.models.TournamentsPlayersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentsPlayersRepository extends JpaRepository<TournamentsPlayersModel, UUID> {
    List<TournamentsPlayersModel> findByPlayerId(UUID id);

    List<TournamentsPlayersModel> findByTournamentId(UUID id);

    Optional<TournamentsPlayersModel> findByTournamentIdAndPlayerId(UUID tournamentId, UUID playerId);
}
