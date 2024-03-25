package com.api.pokerclub.repositories;

import com.api.pokerclub.models.RankingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankingRepository extends JpaRepository<RankingModel, UUID> {

}
