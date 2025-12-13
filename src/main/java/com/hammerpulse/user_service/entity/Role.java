package com.hammerpulse.user_service.entity;

import com.hammerpulse.user_service.enums.USER_ROLES;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private USER_ROLES role;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;
}
