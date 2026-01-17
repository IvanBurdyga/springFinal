package com.javarush.springfinal.model.user;

import jakarta.validation.constraints.Positive;

public class UserRequest {
    @Positive
    private Long id;

    private String name;

    private String email;

    private String password;
}
