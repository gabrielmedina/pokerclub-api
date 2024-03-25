package com.api.pokerclub.services;

import com.api.pokerclub.models.TournamentsPlayersModel;
import com.api.pokerclub.repositories.TournamentsPlayersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentsPlayersService {
    @Autowired
    TournamentsPlayersRepository tournamentsPlayersRepository;

    @Transactional
    public TournamentsPlayersModel save(TournamentsPlayersModel tournamentPlayersModel) {
        return tournamentsPlayersRepository.save(tournamentPlayersModel);
    }

    public Page<TournamentsPlayersModel> findAll(Pageable pageable) {
        return tournamentsPlayersRepository.findAll(pageable);
    }

    public List<TournamentsPlayersModel> findByPlayerId(UUID id) {
        return tournamentsPlayersRepository.findByPlayerId(id);
    }

    public List<TournamentsPlayersModel> findByTournamentId(UUID id) {
        return tournamentsPlayersRepository.findByTournamentId(id);
    }

    public Optional<TournamentsPlayersModel> findByTournamentIdAndPlayerId(UUID tournamentId, UUID playerId) {
        return tournamentsPlayersRepository.findByTournamentIdAndPlayerId(tournamentId, playerId);
    }

    @Transactional
    public void delete(TournamentsPlayersModel tournamentPlayersModel) {
        tournamentsPlayersRepository.delete(tournamentPlayersModel);
    }
}
