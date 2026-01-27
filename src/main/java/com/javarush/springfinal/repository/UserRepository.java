package com.javarush.springfinal.repository;

import com.javarush.springfinal.model.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends RepositoryInterface<User, Long> {

    User findByName(String username);

}
