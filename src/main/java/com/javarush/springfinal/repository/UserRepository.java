package com.javarush.springfinal.repository;

import com.javarush.springfinal.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends RepositoryInterface<User, Long> {

    Optional<User> findByName(String username);

}
