package com.hammerpulse.user_service.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "AddressLine1 is mandatory")
    private String addressLine1;
    private String addressLine2;
    @NotNull(message = "City is mandatory")
    private String city;
    @NotNull(message = "State is mandatory")
    private String state;
    @NotNull(message = "Country is mandatory")
    private String country;
    @NotNull(message = "Pincode is mandatory")
    private String pincode;
    private String landmark;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
