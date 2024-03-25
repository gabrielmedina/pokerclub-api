package com.api.pokerclub.controllers;

import com.api.pokerclub.dtos.TournamentDto;
import com.api.pokerclub.models.TournamentModel;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tournaments")
public class TournamentController {
    @Autowired
    TournamentService tournamentService;

    @GetMapping
    public ResponseEntity<Page<TournamentModel>> getAllTournament(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(tournamentService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneTournament(@PathVariable(value = "id") UUID id) {
        Optional<TournamentModel> tournamentModelOptional = tournamentService.findById(id);

        if (!tournamentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tournamentModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveTournament(@RequestBody @Valid TournamentDto tournamentDto) {
        var tournamentModel = new TournamentModel();
        BeanUtils.copyProperties(tournamentDto, tournamentModel);
        tournamentModel.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(tournamentService.save(tournamentModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTournament(@PathVariable(value = "id") UUID id) {
        Optional<TournamentModel> tournamentModelOptional = tournamentService.findById(id);

        if (!tournamentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        tournamentService.delete(tournamentModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Tournament removed.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putTournament(@PathVariable(value = "id") UUID id,
                                            @RequestBody @Valid TournamentDto tournamentDto) {
        Optional<TournamentModel> tournamentModelOptional = tournamentService.findById(id);

        if (!tournamentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        var tournamentModel = new TournamentModel();
        BeanUtils.copyProperties(tournamentDto, tournamentModel);
        tournamentModel.setId(tournamentModelOptional.get().getId());
        tournamentModel.setCreatedAt(tournamentModelOptional.get().getCreatedAt());

        return ResponseEntity.status(HttpStatus.OK).body(tournamentService.save(tournamentModel));
    }
}
