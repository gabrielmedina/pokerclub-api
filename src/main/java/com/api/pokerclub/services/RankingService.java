package com.api.pokerclub.services;

import com.api.pokerclub.models.RankingModel;
import com.api.pokerclub.repositories.RankingRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class RankingService {
    @Autowired
    RankingRepository rankingRepository;

    @Transactional
    public RankingModel save(RankingModel rankingModel) {
        return rankingRepository.save(rankingModel);
    }

    public Page<RankingModel> findAll(Pageable pageable) {
        return rankingRepository.findAll(pageable);
    }

    public Optional<RankingModel> findById(UUID id) {
        return rankingRepository.findById(id);
    }

    @Transactional
    public void delete(RankingModel rankingModel) {
        rankingRepository.delete(rankingModel);
    }
}
