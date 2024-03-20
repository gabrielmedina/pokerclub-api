package com.api.pokerclub.repositories;

import com.api.pokerclub.models.TournamentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TournamentRepository extends JpaRepository<TournamentModel, UUID> {

}
