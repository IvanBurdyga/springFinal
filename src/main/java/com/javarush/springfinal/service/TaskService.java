package com.javarush.springfinal.service;

import com.javarush.springfinal.mapper.TaskMapper;
import com.javarush.springfinal.model.task.TaskRequest;
import com.javarush.springfinal.model.task.TaskResponse;
import com.javarush.springfinal.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponse> getAllTasks() {
        return taskRepository
                .getAll()
                .map(taskMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        return taskRepository
                .getById(id)
                .map(taskMapper::toTaskResponse)
                .orElseThrow();
    }

    public TaskResponse createTask(TaskRequest taskRequest) {
        return taskRepository
                .create(taskMapper.toTask(taskRequest))
                .map(taskMapper::toTaskResponse)
                .orElseThrow();
    }

    public TaskResponse updateTask(TaskRequest taskRequest) {
        return taskRepository
                .update(taskMapper.toTask(taskRequest))
                .map(taskMapper::toTaskResponse)
                .orElseThrow();
    }

    public boolean deleteUser(Long id) {
        return taskRepository.deleteEntityById(id);
    }
}
