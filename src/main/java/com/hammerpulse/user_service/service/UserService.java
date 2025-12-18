package com.hammerpulse.user_service.service;

import com.hammerpulse.user_service.dto.LoginRequestDto;
import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.entity.User;
import com.hammerpulse.user_service.entity.UserPrincipal;
import com.hammerpulse.user_service.mapper.UserMapper;
import com.hammerpulse.user_service.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    public UserDto registerUser(UserDto userDto){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user=userMapper.toEntity(userDto);
        user=userRepo.save(user);
        return userMapper.toDto(user);
    }

    public String verify(LoginRequestDto loginRequestDto){
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        if(auth.isAuthenticated()){
            UserPrincipal principleUser=(UserPrincipal)auth.getPrincipal();
            User authenticatedUser=principleUser.getUser();
            return jwtService.generateToken(principleUser);
        }
        return "Invalid credentials";
    }


}
