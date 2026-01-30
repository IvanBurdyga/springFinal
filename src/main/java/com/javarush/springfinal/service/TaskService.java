package com.javarush.springfinal.service;

import com.javarush.springfinal.mapper.TaskMapper;
import com.javarush.springfinal.model.TaskStatus;
import com.javarush.springfinal.model.task.Task;
import com.javarush.springfinal.config.dto.TaskRequest;
import com.javarush.springfinal.config.dto.TaskResponse;
import com.javarush.springfinal.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TaskResponse> getAllUserTasks(Long userId) {
        return taskRepository
                .getAllUserTasks(userId)
                .map(this::taskStatusValidation)
                .map(taskMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskResponse createTask(TaskRequest taskRequest) {
        return taskRepository
                .create(taskMapper.toTask(taskRequest))
                .map(this::taskStatusValidation)
                .map(taskMapper::toTaskResponse)
                .orElseThrow();
    }

    @Transactional
    public TaskResponse updateTask(TaskRequest taskRequest) {
        return taskRepository
                .update(taskMapper.toTask(taskRequest))
                .map(this::taskStatusValidation)
                .map(taskMapper::toTaskResponse)
                .orElseThrow();
    }

    @Transactional
    public boolean deleteUser(Long id) {
        return taskRepository.deleteEntityById(id);
    }

    private Task taskStatusValidation(Task task) {
        if (task.getStatus() == TaskStatus.IN_PROGRESS && task.getDeadline().isBefore(LocalDate.now())){
            task.setStatus(TaskStatus.FAILED);
        }
        return task;
    }
}
