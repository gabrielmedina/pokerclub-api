package com.api.pokerclub.controllers;

import com.api.pokerclub.dtos.PlayerDto;
import com.api.pokerclub.models.PlayerModel;
import com.api.pokerclub.services.PlayerService;
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
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping
    public ResponseEntity<Object> savePlayer(@RequestBody @Valid PlayerDto playerDto) {
        if (playerService.existsByName(playerDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: player name in use.");
        }

        var playerModel = new PlayerModel();
        BeanUtils.copyProperties(playerDto, playerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(playerModel));
    }

    @GetMapping
    public ResponseEntity<Page<PlayerModel>> getAllPlayers(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(playerService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePlayer(@PathVariable(value = "id") UUID id) {
        Optional<PlayerModel> playerModelOptional = playerService.findById(id);

        if (!playerModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(playerModelOptional.get());
    }
}
