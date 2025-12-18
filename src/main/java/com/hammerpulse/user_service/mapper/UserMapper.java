package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.entity.Role;
import com.hammerpulse.user_service.entity.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

}
