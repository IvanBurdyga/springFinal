package com.javarush.springfinal.model.task;

import com.javarush.springfinal.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record TaskRequest (
        @Positive
        Long id,
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotBlank
        String deadline,
        @NotBlank
        TaskStatus status,
        @Positive
        @NotBlank
        Long userId) {
}
