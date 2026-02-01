package com.javarush.springfinal.config.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record UserRequest(
        @Positive
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
