package com.webrise.testproject.mapper;

import com.webrise.testproject.model.dto.UserDTO;
import com.webrise.testproject.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    List<UserDTO> toDtoList(List<User> user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    User toEntity(UserDTO userDTO);
}
