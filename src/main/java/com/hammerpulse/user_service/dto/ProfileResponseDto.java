package com.hammerpulse.user_service.dto;

import com.hammerpulse.user_service.enums.USER_STATUS;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Date;

@Data
public class ProfileResponseDto {
    private String name;
    private String username;
    private String email;
    private USER_STATUS user_status;
    private Date dob;
    @Size(min =8,message = "Please enter a valid phone number")
    private String phoneNumber;
    private String profilePic;
    public ProfileResponseDto(String name, String username, String email, USER_STATUS userStatus, Date dob, String phoneNumber, String profilePic) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.user_status = userStatus;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.profilePic = profilePic;
    }
}
