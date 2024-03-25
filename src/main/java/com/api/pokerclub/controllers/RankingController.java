package com.api.pokerclub.controllers;


import com.api.pokerclub.dtos.RankingDto;
import com.api.pokerclub.models.RankingModel;
import com.api.pokerclub.models.TournamentModel;
import com.api.pokerclub.services.RankingService;
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
@RequestMapping("/ranking")
public class RankingController {
    @Autowired
    RankingService rankingService;

    @Autowired
    TournamentService tournamentService;


    @GetMapping
    public ResponseEntity<Page<RankingModel>> getAllRankings(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(rankingService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneRanking(@PathVariable(value = "id") UUID id) {
        Optional<RankingModel> rankingModelOptional = rankingService.findById(id);

        if (!rankingModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(rankingModelOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> saveRanking(@RequestBody @Valid RankingDto rankingDto) {
        var rankingModel = new RankingModel();
        BeanUtils.copyProperties(rankingDto, rankingModel);
        rankingModel.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.OK).body(rankingService.save(rankingModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteRanking(@PathVariable(value = "id") UUID id) {
        Optional<RankingModel> rankingModelOptional = rankingService.findById(id);

        if (!rankingModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking not found.");
        }

        rankingService.delete(rankingModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Ranking removed.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> putRanking(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid RankingDto rankingDto) {
        Optional<RankingModel> rankingModelOptional = rankingService.findById(id);

        if (!rankingModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking not found.");
        }

        var rankingModel = new RankingModel();
        BeanUtils.copyProperties(rankingDto, rankingModel);
        rankingModel.setId(rankingModelOptional.get().getId());
        rankingModel.setCreatedAt(rankingModelOptional.get().getCreatedAt());

        return ResponseEntity.status(HttpStatus.OK).body(rankingService.save(rankingModel));
    }

    @PutMapping("/{id}/tournament/{tournamentId}")
    public ResponseEntity<Object> putRankingTournaments(
            @PathVariable(value = "id") UUID id,
            @PathVariable(value = "tournamentId") UUID tournamentId) {
        Optional<RankingModel> rankingModelOptional = rankingService.findById(id);

        if (!rankingModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ranking not found.");
        }

        var rankingModel = new RankingModel();
        rankingModel.setId(rankingModelOptional.get().getId());
        rankingModel.setTitle(rankingModelOptional.get().getTitle());
        rankingModel.setCreatedAt(rankingModelOptional.get().getCreatedAt());

        Optional<TournamentModel> tournamentModelOptional = tournamentService.findById(tournamentId);

        if (!tournamentModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tournament not found.");
        }

        var tournaments = rankingModelOptional.get().getTournaments();
        tournaments.add(tournamentModelOptional.get());
        rankingModel.setTournaments(tournaments);

        return ResponseEntity.status(HttpStatus.OK).body(rankingService.save(rankingModel));
    }
}
