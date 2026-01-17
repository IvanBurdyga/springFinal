package com.javarush.springfinal.controller;

import com.javarush.springfinal.model.user.UserRequest;
import com.javarush.springfinal.model.user.UserResponse;
import com.javarush.springfinal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private UserResponse readUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    private UserResponse updateUser(@Valid @RequestBody UserRequest userRequest){
        return userService.updateUser(userRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
