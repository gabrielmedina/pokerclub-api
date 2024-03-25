package com.api.pokerclub.dtos;

import java.util.UUID;

public class TournamentsPlayersDto {
    private UUID player;
    private UUID tournament;
    private double prize;
    private int points;
    private int finishPosition;

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }

    public UUID getTournament() {
        return tournament;
    }

    public void setTournament(UUID tournament) {
        this.tournament = tournament;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(int finishPosition) {
        this.finishPosition = finishPosition;
    }
}
