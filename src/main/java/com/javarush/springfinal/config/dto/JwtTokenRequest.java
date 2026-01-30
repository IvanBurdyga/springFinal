package com.javarush.springfinal.config.dto;

public record JwtTokenRequest (
        String name,
        String password
) {
}
