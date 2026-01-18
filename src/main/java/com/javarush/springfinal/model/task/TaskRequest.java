package com.javarush.springfinal.model.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TaskRequest(
        @Positive
        Long id,
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String deadline,
        @NotBlank
        String status,
        @NotBlank
        Long userId) {
}
