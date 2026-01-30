package com.javarush.springfinal.config.dto;

public record UserResponse (
    Long id,
    String name,
    String email
    ) {
}
