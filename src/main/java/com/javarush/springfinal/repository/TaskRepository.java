package com.javarush.springfinal.repository;

import com.javarush.springfinal.model.task.Task;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends RepositoryInterface<Task, Long> {
}
