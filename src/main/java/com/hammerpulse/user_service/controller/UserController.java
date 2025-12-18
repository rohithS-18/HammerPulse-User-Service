package com.hammerpulse.user_service.controller;

import com.hammerpulse.user_service.dto.LoginRequestDto;
import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("auth/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        UserDto user=userService.registerUser(userDto);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto){
        log.info("Authenticating user :{}", loginRequestDto.getUsername());
        String jwt=userService.verify(loginRequestDto);
        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return new ResponseEntity<>("success",HttpStatus.OK);
    }


}
