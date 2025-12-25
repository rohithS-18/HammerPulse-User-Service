package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.entity.Role;
import com.hammerpulse.user_service.entity.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);

}
