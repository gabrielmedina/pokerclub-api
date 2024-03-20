package com.api.pokerclub.controllers;

import com.api.pokerclub.dtos.TournamentDto;
import com.api.pokerclub.dtos.TournamentPlayersDto;
import com.api.pokerclub.models.TournamentModel;
import com.api.pokerclub.models.TournamentPlayersModel;
import com.api.pokerclub.services.PlayerService;
import com.api.pokerclub.services.TournamentPlayersService;
import com.api.pokerclub.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tournaments-players")
public class TournamentPlayersController {
    @Autowired
    TournamentPlayersService tournamentPlayersService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<TournamentPlayersModel>> getAllTournamentPlayers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(tournamentPlayersService.findAll(pageable));
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Object> getTournamentsByPlayer(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(tournamentPlayersService.findByPlayerId(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveTournamentPlayers(@RequestBody @Valid TournamentPlayersDto tournamentPlayersDto) {
        var tournamentPlayersModel = new TournamentPlayersModel();
        BeanUtils.copyProperties(tournamentPlayersDto, tournamentPlayersModel);

        var player = playerService.findById(tournamentPlayersDto.getPlayer());
        var tournament = tournamentService.findById(tournamentPlayersDto.getTournament());

        if (player.isPresent() && tournament.isPresent()) {
            tournamentPlayersModel.setPlayer(player.get());
            tournamentPlayersModel.setTournament(tournament.get());

            return ResponseEntity.status(HttpStatus.OK).body(tournamentPlayersService.save(tournamentPlayersModel));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament or player not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTournamentPlayers(@PathVariable(value = "id") UUID id) {
        Optional<TournamentPlayersModel> tournamentPlayersModelOptional = tournamentPlayersService.findById(id);

        if (!tournamentPlayersModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        tournamentPlayersService.delete(tournamentPlayersModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Player removed from tournament.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putTournamentPlayers(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid TournamentPlayersDto tournamentPlayersDto) {
        Optional<TournamentPlayersModel> tournamentPlayersModelOptional = tournamentPlayersService.findById(id);

        if (!tournamentPlayersModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        var tournamentPlayersModel = new TournamentPlayersModel();
        BeanUtils.copyProperties(tournamentPlayersDto, tournamentPlayersModel);
        tournamentPlayersModel.setId(tournamentPlayersModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(tournamentPlayersService.save(tournamentPlayersModel));
    }
}
