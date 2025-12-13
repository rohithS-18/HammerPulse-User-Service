package com.hammerpulse.user_service.service;

import com.hammerpulse.user_service.dto.UserDto;
import com.hammerpulse.user_service.entity.User;
import com.hammerpulse.user_service.mapper.UserMapper;
import com.hammerpulse.user_service.repository.UserRepo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;

    public UserDto registerUser(UserDto userDto){
        User user=userMapper.toEntity(userDto);
        user=userRepo.save(user);
        return userMapper.toDto(user);
    }


}
