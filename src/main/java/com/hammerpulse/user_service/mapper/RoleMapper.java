package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleDto dto);

    RoleDto toDto(Role role);
}
