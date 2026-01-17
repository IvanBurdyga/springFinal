package com.javarush.springfinal.repository;

import com.javarush.springfinal.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    default Stream<User> getAll() {
        return StreamSupport.stream(findAll().spliterator(), false);
    }

    default Optional<User> getById(Long id) {
        return findById(id);
    }

    default Optional<User> create(User input) {
        User saved = save(input);
        return Optional.of(saved);
    }

    default Optional<User> update(User input) {
        User updated = save(input);
        return Optional.of(updated);
    }

    default boolean deleteEntityById(Long id) {
        boolean deleted = false;
        if (existsById(id)) {
            deleteById(id);
            deleted = true;
        }
        return deleted;
    }
}
