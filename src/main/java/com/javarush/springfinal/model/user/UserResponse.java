package com.javarush.springfinal.model.user;

public record UserResponse (
    Long id,

    String name,

    String email
    ) {
}
