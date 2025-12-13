package com.hammerpulse.user_service.controller;

import com.hammerpulse.user_service.dto.LoginRequest;
import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.entity.User;
import com.hammerpulse.user_service.repository.UserRepo;
import com.hammerpulse.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        UserDto user=userService.registerUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){

    }

}
