package com.api.pokerclub.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class TournamentPlayersModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerModel player;

    @ManyToOne
    @JoinColumn(name = "tournament_id", referencedColumnName = "id")
    private TournamentModel tournament;

    @Column
    private int points;

    @Column
    private double prize;

    @Column
    private int finishPosition;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    public TournamentModel getTournament() {
        return tournament;
    }

    public void setTournament(TournamentModel tournament) {
        this.tournament = tournament;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int getFinishPosition() {
        return finishPosition;
    }

    public void setFinishPosition(int finishPosition) {
        this.finishPosition = finishPosition;
    }
}
