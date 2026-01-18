package com.javarush.springfinal.mapper;

import com.javarush.springfinal.model.task.Task;
import com.javarush.springfinal.model.task.TaskRequest;
import com.javarush.springfinal.model.task.TaskResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TaskMapper {
    @Mapping(source = "userId", target = "user.id")
    Task toTask(TaskRequest taskRequest);

    @Mapping(source = "user.id", target = "userId")
    TaskResponse toTaskResponse(Task task);
}
