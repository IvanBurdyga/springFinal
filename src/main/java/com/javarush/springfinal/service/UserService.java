package com.javarush.springfinal.service;

import com.javarush.springfinal.mapper.UserMapper;
import com.javarush.springfinal.model.user.UserRequest;
import com.javarush.springfinal.model.user.UserResponse;
import com.javarush.springfinal.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    public List<User> getAllUsers() {}
    public UserResponse getUserById(Long id) {
        return userRepository
                .getById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow();
    }
    public UserResponse createUser(UserRequest userRequest) {
        return userRepository
                .create(userMapper.toUser(userRequest))
                .map(userMapper::toUserResponse)
                .orElseThrow();
    }
    public UserResponse updateUser(UserRequest userRequest) {
        return userRepository
                .update(userMapper.toUser(userRequest))
                .map(userMapper::toUserResponse)
                .orElseThrow();
    }
    public boolean deleteUser(Long id) {
        return userRepository.deleteEntityById(id);
    }


}
