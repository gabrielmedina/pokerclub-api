package com.api.pokerclub.services;

import com.api.pokerclub.models.PlayerModel;
import com.api.pokerclub.models.TournamentPlayersModel;
import com.api.pokerclub.repositories.TournamentPlayersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentPlayersService {
    @Autowired
    TournamentPlayersRepository tournamentPlayersRepository;

    @Transactional
    public TournamentPlayersModel save(TournamentPlayersModel tournamentPlayersModel) {
        return tournamentPlayersRepository.save(tournamentPlayersModel);
    }

    public Page<TournamentPlayersModel> findAll(Pageable pageable) {
        return tournamentPlayersRepository.findAll(pageable);
    }

    public Optional<TournamentPlayersModel> findById(UUID id) {
        return tournamentPlayersRepository.findById(id);
    }

    public List<TournamentPlayersModel> findByPlayerId(UUID id) {
        return tournamentPlayersRepository.findByPlayerId(id);
    }

    @Transactional
    public void delete(TournamentPlayersModel tournamentPlayersModel) {
        tournamentPlayersRepository.delete(tournamentPlayersModel);
    }
}
