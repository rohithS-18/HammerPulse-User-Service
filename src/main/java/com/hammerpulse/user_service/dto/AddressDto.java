package com.hammerpulse.user_service.dto;

import lombok.Data;

@Data
public class AddressDto {
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String landmark;
    private int userId;
}
