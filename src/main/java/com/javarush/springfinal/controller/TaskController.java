package com.javarush.springfinal.controller;

import com.javarush.springfinal.model.task.TaskRequest;
import com.javarush.springfinal.model.task.TaskResponse;
import com.javarush.springfinal.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private TaskResponse readTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    private Collection<TaskResponse> readTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.createTask(taskRequest);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    private TaskResponse updateTask(@Valid @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(taskRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private boolean deleteTask(@PathVariable Long id) {
        return taskService.deleteUser(id);
    }


}
