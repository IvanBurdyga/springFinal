package com.javarush.springfinal.service;

import com.javarush.springfinal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticateService implements UserDetailsService {

    private final UserRepository userRepository;


    @NullMarked
    @NonNull
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userInDb = userRepository.findByName(username).orElseThrow();
        return User.withUsername(userInDb.getName())
                .password(userInDb.getPassword())
                .roles(userInDb.getRole().getRole())
                .build();
    }
}
