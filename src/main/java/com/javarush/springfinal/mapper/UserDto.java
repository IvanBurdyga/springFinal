package com.javarush.springfinal.mapper;

import com.javarush.springfinal.model.user.User;
import com.javarush.springfinal.model.user.UserRequest;
import com.javarush.springfinal.model.user.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserDto {

    User toUser(UserRequest userRequest);

    UserResponse toUserResponse(User user);
}
