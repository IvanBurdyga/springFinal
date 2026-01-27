package com.javarush.springfinal.controller;

import com.javarush.springfinal.exception.CreateTaskException;
import com.javarush.springfinal.exception.InvalidUserIdForTasksException;
import com.javarush.springfinal.exception.UpdateTaskException;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/task")
public class TaskController {


    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    private Collection<TaskResponse> readAllUserTasks(@PathVariable Long userId) {
        try{
            return taskService.getAllUserTasks(userId);
        } catch (InvalidUserIdForTasksException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    private TaskResponse createTask(@Valid @RequestBody TaskRequest taskRequest) {
        try{
            return taskService.createTask(taskRequest);
        } catch (CreateTaskException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    private TaskResponse updateTask(@Valid @RequestBody TaskRequest taskRequest) {
        try {
            return taskService.updateTask(taskRequest);
        } catch (UpdateTaskException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteTask(@PathVariable Long id) {
        boolean result = taskService.deleteUser(id);
        if (!result){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

}
