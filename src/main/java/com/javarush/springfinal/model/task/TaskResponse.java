package com.javarush.springfinal.model.task;

import jakarta.validation.constraints.Positive;

public record TaskResponse(
        @Positive
        Long id,
        String title,
        String description,
        String deadline,
        String status,
        Long userId
) {
}
