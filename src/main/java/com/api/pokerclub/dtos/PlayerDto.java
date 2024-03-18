package com.api.pokerclub.dtos;

import jakarta.validation.constraints.NotBlank;

public class PlayerDto {
    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
