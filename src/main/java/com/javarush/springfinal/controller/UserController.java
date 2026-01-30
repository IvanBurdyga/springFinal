package com.javarush.springfinal.controller;

import com.javarush.springfinal.exception.UpdateUserException;
import com.javarush.springfinal.exception.UserNotFoundException;
import com.javarush.springfinal.config.dto.UserRequest;
import com.javarush.springfinal.config.dto.UserResponse;
import com.javarush.springfinal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable String id) {
        try {
            return userService.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    private UserResponse updateUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            return userService.updateUser(userRequest);
        } catch (UpdateUserException e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteUser(@PathVariable Long id) {
        boolean result = userService.deleteUser(id);
        if (!result) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}

