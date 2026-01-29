package com.javarush.springfinal.config.jwt.model;

public record JwtTokenRequest (
        String name,
        String password
) {
}
