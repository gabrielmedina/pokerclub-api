package com.api.pokerclub.dtos;

import com.api.pokerclub.models.TournamentModel;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

public class RankingDto {
    @NotBlank
    private String title;

    private List<TournamentModel> tournaments;
    private LocalDateTime finishedAt;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TournamentModel> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentModel> tournaments) {
        this.tournaments = tournaments;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }
}
