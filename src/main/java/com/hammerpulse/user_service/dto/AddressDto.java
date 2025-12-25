package com.hammerpulse.user_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDto {
    private Integer id;
    @NotNull(message = "Adress line cannot be null")
    private String addressLine1;
    private String addressLine2;
    @NotNull(message = "City cannot be null")
    private String city;
    @NotNull(message = "State cannot be null")
    private String state;
    @NotNull(message = "Country cannot be null")
    private String country;
    @NotNull(message = "Pin code cannot be null")
    private String pincode;
    private String landmark;
    @NotNull(message ="User Id must be provided")
    private int userId;
}
