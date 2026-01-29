package com.javarush.springfinal.controller;

import com.javarush.springfinal.config.jwt.model.JwtTokenRequest;
import com.javarush.springfinal.config.jwt.model.JwtTokenResponse;
import com.javarush.springfinal.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    private final TokenService tokenService;

    @PostMapping("/token/create")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public JwtTokenResponse authenticate(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return tokenService.generateToken(jwtTokenRequest);
    }
}
