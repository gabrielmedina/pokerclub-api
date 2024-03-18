package com.api.pokerclub.services;

import com.api.pokerclub.models.PlayerModel;
import com.api.pokerclub.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Transactional
    public PlayerModel save(PlayerModel playerModel) {
        return playerRepository.save(playerModel);
    }

    public Page<PlayerModel> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    public Optional<PlayerModel> findById(UUID id) {
        return playerRepository.findById(id);
    }

    @Transactional
    public void delete(PlayerModel playerModel) {
        playerRepository.delete(playerModel);
    }

    public boolean existsByName(String name) {
        return playerRepository.existsByName(name);
    }
}
