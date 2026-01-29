package com.javarush.springfinal.service;

import com.javarush.springfinal.exception.CreateUserException;
import com.javarush.springfinal.exception.UpdateUserException;
import com.javarush.springfinal.mapper.UserMapper;
import com.javarush.springfinal.model.Role;
import com.javarush.springfinal.model.user.User;
import com.javarush.springfinal.model.user.UserRequest;
import com.javarush.springfinal.model.user.UserResponse;
import com.javarush.springfinal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponse> getAllUsers() {
        return userRepository
                .getAll()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(String name) {
        return userRepository
                .findByName(name)
                .map(userMapper::toUserResponse)
                .orElseThrow();
    }

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try{
            return userRepository
                    .create(user)
                    .map(userMapper::toUserResponse)
                    .orElseThrow();
        } catch (Exception e) {
            throw new CreateUserException();
        }
    }

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
        User user = userMapper.toUser(userRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            return userRepository
                    .update(user)
                    .map(userMapper::toUserResponse)
                    .orElseThrow();
        } catch (Exception e) {
            throw new UpdateUserException();
        }

    }

    @Transactional
    public boolean deleteUser(Long id) {
        return userRepository.deleteEntityById(id);
    }


}
