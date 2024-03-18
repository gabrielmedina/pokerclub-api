package com.api.pokerclub.controllers;

import com.api.pokerclub.dtos.PlayerDto;
import com.api.pokerclub.models.PlayerModel;
import com.api.pokerclub.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;

    @PostMapping
    public ResponseEntity<Object> savePlayer(@RequestBody @Valid PlayerDto playerDto) {
        if (playerService.existsByName(playerDto.getName())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: player name in use");
        }

        var playerModel = new PlayerModel();
        BeanUtils.copyProperties(playerDto, playerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.save(playerModel));
    }
}
