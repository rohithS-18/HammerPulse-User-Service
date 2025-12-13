package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.AddressDto;
import com.hammerpulse.user_service.entity.Address;
import org.mapstruct.Mapping;

public interface AddressMapper {
    @Mapping(target = "userId", source = "user.id")
    Address toEntity(AddressDto dto);

    @Mapping(target="user.id" ,source="userId")
    AddressDto toDto(Address role);
}
