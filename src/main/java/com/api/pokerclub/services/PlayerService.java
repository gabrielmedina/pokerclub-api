package com.api.pokerclub.services;

import com.api.pokerclub.models.PlayerModel;
import com.api.pokerclub.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;

    @Transactional
    public PlayerModel save(PlayerModel playerModel) {
        return playerRepository.save(playerModel);
    }

    public boolean existsByName(String name) {
        return playerRepository.existsByName(name);
    }
}
