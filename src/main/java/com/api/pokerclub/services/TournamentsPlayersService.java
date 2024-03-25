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
    TournamentsPlayersRepository tournamentPlayersRepository;

    @Transactional
    public TournamentsPlayersModel save(TournamentsPlayersModel tournamentPlayersModel) {
        return tournamentPlayersRepository.save(tournamentPlayersModel);
    }

    public Page<TournamentsPlayersModel> findAll(Pageable pageable) {
        return tournamentPlayersRepository.findAll(pageable);
    }

    public Optional<TournamentsPlayersModel> findById(UUID id) {
        return tournamentPlayersRepository.findById(id);
    }

    public List<TournamentsPlayersModel> findByPlayerId(UUID id) {
        return tournamentPlayersRepository.findByPlayerId(id);
    }

    public List<TournamentsPlayersModel> findByTournamentId(UUID id) {
        return tournamentPlayersRepository.findByTournamentId(id);
    }

    @Transactional
    public void delete(TournamentsPlayersModel tournamentPlayersModel) {
        tournamentPlayersRepository.delete(tournamentPlayersModel);
    }
}
