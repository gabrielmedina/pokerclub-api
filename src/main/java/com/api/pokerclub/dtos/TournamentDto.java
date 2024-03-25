package com.api.pokerclub.dtos;

import com.api.pokerclub.models.TournamentsPlayersModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public class TournamentDto {
    @NotBlank
    private String title;

    @NotNull
    private double buyin;

    private LocalDateTime createdAt;

    private LocalDateTime finishedAt;

    private Set<TournamentsPlayersModel> tournamentsPlayers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBuyin() {
        return buyin;
    }

    public void setBuyin(double buyin) {
        this.buyin = buyin;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public Set<TournamentsPlayersModel> getTournamentsPlayers() {
        return tournamentsPlayers;
    }

    public void setTournamentsPlayers(Set<TournamentsPlayersModel> tournamentsPlayers) {
        this.tournamentsPlayers = tournamentsPlayers;
    }
}
