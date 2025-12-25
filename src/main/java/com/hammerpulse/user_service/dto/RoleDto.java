package com.hammerpulse.user_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private Integer id;
    @NotNull(message = "Role name must be provided")
    private String name;
    @Size(max = 50)
    private String description;
}
