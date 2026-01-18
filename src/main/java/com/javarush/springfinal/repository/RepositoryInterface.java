package com.javarush.springfinal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface RepositoryInterface<T, ID> extends CrudRepository<T, ID> {

    default Stream<T> getAll() {
        return StreamSupport.stream(findAll().spliterator(), false);
    }

    default Optional<T> getById(ID id) {
        return findById(id);
    }

    default Optional<T> create(T input) {
        T saved = save(input);
        return Optional.of(saved);
    }

    default Optional<T> update(T input) {
        T updated = save(input);
        return Optional.of(updated);
    }

    default boolean deleteEntityById(ID id) {
        boolean deleted = false;
        if (existsById(id)) {
            deleteById(id);
            deleted = true;
        }
        return deleted;
    }
}

