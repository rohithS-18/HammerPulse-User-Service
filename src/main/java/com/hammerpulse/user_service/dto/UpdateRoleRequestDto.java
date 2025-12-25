package com.hammerpulse.user_service.dto;

import lombok.Data;

@Data
public class UpdateRoleRequestDto {
    String username;
    int roleId;
}
