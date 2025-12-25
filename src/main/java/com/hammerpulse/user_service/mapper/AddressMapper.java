package com.hammerpulse.user_service.mapper;

import com.hammerpulse.user_service.dto.AddressDto;
import com.hammerpulse.user_service.entity.Address;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "userId", target = "user.id")
    Address toEntity(AddressDto dto);

    @Mapping(source="user.id" ,target="userId")
    AddressDto toDto(Address role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDto(AddressDto dto, @MappingTarget Address entity);
}
