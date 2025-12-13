package com.hammerpulse.user_service.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    private int id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String pincode;
    private String landmark;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
