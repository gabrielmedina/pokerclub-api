package com.api.pokerclub.controllers;

import com.api.pokerclub.dtos.TournamentsPlayersDto;
import com.api.pokerclub.models.TournamentsPlayersModel;
import com.api.pokerclub.services.PlayerService;
import com.api.pokerclub.services.TournamentsPlayersService;
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
public class TournamentsPlayersController {
    @Autowired
    TournamentsPlayersService tournamentsPlayersService;

    @Autowired
    TournamentService tournamentService;

    @Autowired
    PlayerService playerService;

    @GetMapping
    public ResponseEntity<Page<TournamentsPlayersModel>> getAllTournamentsPlayers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(tournamentsPlayersService.findAll(pageable));
    }

    @GetMapping("/player/{id}")
    public ResponseEntity<Object> getTournamentsPlayersByPlayer(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(tournamentsPlayersService.findByPlayerId(id));
    }

    @GetMapping("/tournament/{id}")
    public ResponseEntity<Object> getTournamentsPlayersByTournament(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(tournamentsPlayersService.findByTournamentId(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveTournamentsPlayers(@RequestBody @Valid TournamentsPlayersDto tournamentsPlayersDto) {
        var tournamentsPlayersModel = new TournamentsPlayersModel();
        BeanUtils.copyProperties(tournamentsPlayersDto, tournamentsPlayersModel);

        var player = playerService.findById(tournamentsPlayersDto.getPlayer());
        var tournament = tournamentService.findById(tournamentsPlayersDto.getTournament());

        if (player.isPresent() && tournament.isPresent()) {
            tournamentsPlayersModel.setPlayer(player.get());
            tournamentsPlayersModel.setTournament(tournament.get());

            return ResponseEntity.status(HttpStatus.OK).body(tournamentsPlayersService.save(tournamentsPlayersModel));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament or player not found.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTournamentsPlayers(@PathVariable(value = "id") UUID id) {
        Optional<TournamentsPlayersModel> tournamentsPlayersModelOptional = tournamentsPlayersService.findById(id);

        if (!tournamentsPlayersModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        tournamentsPlayersService.delete(tournamentsPlayersModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Player removed from tournament.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putTournamentsPlayers(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid TournamentsPlayersDto tournamentsPlayersDto) {
        Optional<TournamentsPlayersModel> tournamentsPlayersModelOptional = tournamentsPlayersService.findById(id);

        if (!tournamentsPlayersModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        var tournamentsPlayersModel = new TournamentsPlayersModel();
        BeanUtils.copyProperties(tournamentsPlayersDto, tournamentsPlayersModel);
        tournamentsPlayersModel.setId(tournamentsPlayersModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(tournamentsPlayersService.save(tournamentsPlayersModel));
    }
}
