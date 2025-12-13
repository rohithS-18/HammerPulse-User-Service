package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "userId", target = "user.id")
    Role toEntity(RoleDto dto);

    @Mapping(source="user.id" ,target="userId")
    RoleDto toDto(Role role);
}
