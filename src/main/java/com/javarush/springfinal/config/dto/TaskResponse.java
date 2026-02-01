package com.javarush.springfinal.config.dto;

import com.javarush.springfinal.model.TaskStatus;
import jakarta.validation.constraints.Positive;

public record TaskResponse(
        @Positive
        Long id,
        String title,
        String description,
        String deadline,
        TaskStatus status,
        Long userId
) {
}
