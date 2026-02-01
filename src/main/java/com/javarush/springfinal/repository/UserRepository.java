package com.javarush.springfinal.repository;

import com.javarush.springfinal.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends RepositoryInterface<User, Long> {

    @Query("select u from User u where u.name like :name")
    Optional<User> getUserByName(@Param("name") String name);

}
