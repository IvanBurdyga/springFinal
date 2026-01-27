package com.javarush.springfinal.controller.http;

import com.javarush.springfinal.model.user.UserRequest;
import com.javarush.springfinal.model.user.UserResponse;
import com.javarush.springfinal.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
@AllArgsConstructor
@RequestMapping("/register")
public class RegisterPageController {

    @GetMapping
    public String showRegistrationForm() {
        return "register";
    }
}
