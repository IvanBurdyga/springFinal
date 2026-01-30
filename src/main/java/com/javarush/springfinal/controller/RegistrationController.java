package com.javarush.springfinal.controller;

import com.javarush.springfinal.exception.CreateUserException;
import com.javarush.springfinal.config.dto.UserRequest;
import com.javarush.springfinal.config.dto.UserResponse;
import com.javarush.springfinal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@AllArgsConstructor
@RequestMapping("/registration/user")
public class RegistrationController {

    private final UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private UserResponse createUser(@RequestBody UserRequest userRequest) {
        try {
            return userService.createUser(userRequest);
        } catch (CreateUserException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }
}
