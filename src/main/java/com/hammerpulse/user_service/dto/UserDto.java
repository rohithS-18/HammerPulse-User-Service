package com.hammerpulse.user_service.dto;

import com.hammerpulse.user_service.enums.USER_STATUS;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "email cannot be empty")
    private String name;
    @NotNull(message = "username cannot be empty")
    private String username;
    @NotNull(message = "email cannot be empty")
    @Email
    private String email;
    @NotNull(message = "password cannot be empty")
    @Size(min = 8,message = "password cannot be less than 8 characters")
    private String password;
    private USER_STATUS user_status;
    private Date dob;
    private String phoneNumber;
    @Column(name = "profile_pic")
    private String profilePic;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    private List<RoleDto> roles;
}
