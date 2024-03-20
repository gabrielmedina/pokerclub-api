package com.api.pokerclub.services;

import com.api.pokerclub.models.TournamentModel;
import com.api.pokerclub.repositories.TournamentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository tournamentRepository;

    @Transactional
    public TournamentModel save(TournamentModel tournamentModel) {
        return tournamentRepository.save(tournamentModel);
    }

    public Page<TournamentModel> findAll(Pageable pageable) {
        return tournamentRepository.findAll(pageable);
    }

    public Optional<TournamentModel> findById(UUID id) {
        return tournamentRepository.findById(id);
    }

    @Transactional
    public void delete(TournamentModel tournamentModel) {
        tournamentRepository.delete(tournamentModel);
    }
}
