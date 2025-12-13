package com.hammerpulse.user_service.dto;

import com.hammerpulse.user_service.enums.USER_ROLES;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RoleDto {
    private int id;
    private USER_ROLES role;
    private int userId;
}
