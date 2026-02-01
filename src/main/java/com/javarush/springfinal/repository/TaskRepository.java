package com.javarush.springfinal.repository;

import com.javarush.springfinal.model.task.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TaskRepository extends RepositoryInterface<Task, Long> {

    @Query("select t from Task t where t.user.id = :userId")
    Stream<Task> getAllUserTasks(@Param("userId") Long userId);
}
