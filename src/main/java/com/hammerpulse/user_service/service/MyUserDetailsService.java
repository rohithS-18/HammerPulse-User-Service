package com.hammerpulse.user_service.service;

import com.hammerpulse.user_service.entity.User;
import com.hammerpulse.user_service.entity.UserPrincipal;
import com.hammerpulse.user_service.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting user by username :"+username);
        User user=userRepo.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User not found");
        }
        redisService.set(username,user,600L);
        return new UserPrincipal(user);
    }

}
