package com.javarush.springfinal.service;

import com.javarush.springfinal.mapper.UserDto;
import com.javarush.springfinal.model.user.UserRequest;
import com.javarush.springfinal.model.user.UserResponse;
import com.javarush.springfinal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDto userDto;

//    public List<User> getAllUsers() {}
    public UserResponse getUserById(Long id) {
        return userRepository
                .getById(id)
                .map(userDto::toUserResponse)
                .orElseThrow();
    }
    public UserResponse createUser(UserRequest userRequest) {
        return userRepository
                .create(userDto.toUser(userRequest))
                .map(userDto::toUserResponse)
                .orElseThrow();
    }
    public UserResponse updateUser(UserRequest userRequest) {
        return userRepository
                .update(userDto.toUser(userRequest))
                .map(userDto::toUserResponse)
                .orElseThrow();
    }
    public boolean deleteUser(Long id) {
        return userRepository.deleteEntityById(id);
    }


}
