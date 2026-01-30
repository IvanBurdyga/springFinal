package com.javarush.springfinal.service;

import com.javarush.springfinal.config.jwt.JwtTokenHelper;
import com.javarush.springfinal.config.dto.JwtTokenRequest;
import com.javarush.springfinal.config.dto.JwtTokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class TokenService {

    private final AuthenticationManager authenticationManager;

    private final AuthenticateService authenticateService;

    private final JwtTokenHelper jwtTokenHelper;

    public JwtTokenResponse generateToken(JwtTokenRequest tokenRequest) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        tokenRequest.name(), tokenRequest.password()));
        final UserDetails userDetails =
                authenticateService.loadUserByUsername(tokenRequest.name());
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", userDetails.getUsername());
        String token = jwtTokenHelper.createToken(
                userDetails.getUsername(), claims);
        return new JwtTokenResponse(token);
    }
}
