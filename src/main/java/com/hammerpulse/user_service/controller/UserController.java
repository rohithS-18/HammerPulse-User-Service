package com.hammerpulse.user_service.controller;

import com.hammerpulse.user_service.dto.*;
import com.hammerpulse.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("user/me")
    public ResponseEntity<ProfileResponseDto> getMyProfile(){
        ProfileResponseDto profile=userService.getprofile();
       return ResponseEntity.ok(profile);
    }

    @PatchMapping("/user/{username}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("username") String id, @RequestBody ProfileUpdateDto userDto){
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @PatchMapping("/user/update-role")
    public ResponseEntity<?> updateUserRole(@RequestBody UpdateRoleRequestDto updateRoleRequestDto){
        return ResponseEntity.ok(userService.addRoleToUser(updateRoleRequestDto));
    }

    @GetMapping("/user/roles/{id}")
    public ResponseEntity<AllRolesResponse> getRolesOfUser(@PathVariable("id") int id){
        return ResponseEntity.ok(userService.getRolesOfUser(id));
    }

}
