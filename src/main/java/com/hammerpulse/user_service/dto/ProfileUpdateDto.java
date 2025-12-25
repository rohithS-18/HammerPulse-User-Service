package com.hammerpulse.user_service.dto;

import com.hammerpulse.user_service.enums.USER_STATUS;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class ProfileUpdateDto {
    private String name;
    private String email;
    @Size(min = 8,message = "password cannot be less than 8 characters")
    private String password;
    private String user_status;
    private Date dob;
    private String phoneNumber;
    private String profilePic;
}
