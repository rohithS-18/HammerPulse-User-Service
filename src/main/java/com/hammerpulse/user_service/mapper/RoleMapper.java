package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.entity.Role;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleDto dto);

    RoleDto toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRoleFromDto(RoleDto dto, @MappingTarget Role entity);
}
