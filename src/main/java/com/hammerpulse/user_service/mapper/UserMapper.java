package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.entity.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User entity);

    User toEntity(UserDto dto);
}
