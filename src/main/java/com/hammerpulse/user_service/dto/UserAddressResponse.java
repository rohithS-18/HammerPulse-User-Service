package com.hammerpulse.user_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserAddressResponse {
    List<AddressDto> address;
    int count;
}
