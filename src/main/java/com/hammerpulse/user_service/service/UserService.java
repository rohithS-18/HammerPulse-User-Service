package com.hammerpulse.user_service.service;

import com.hammerpulse.user_service.config.SecurityConfig;
import com.hammerpulse.user_service.dto.*;
import com.hammerpulse.user_service.entity.Role;
import com.hammerpulse.user_service.entity.User;
import com.hammerpulse.user_service.entity.UserPrincipal;
import com.hammerpulse.user_service.enums.USER_STATUS;
import com.hammerpulse.user_service.exception.ResourceNotFoundException;
import com.hammerpulse.user_service.exception.UnauthorizedException;
import com.hammerpulse.user_service.mapper.RoleMapper;
import com.hammerpulse.user_service.mapper.UserMapper;
import com.hammerpulse.user_service.repository.RoleRepo;
import com.hammerpulse.user_service.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Slf4j
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
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private RoleMapper roleMapper;

    public UserDto registerUser(UserDto userDto){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user=userMapper.toEntity(userDto);
        Role role=roleRepo.findById(2);
        user.setRoles(List.of(role));
        try {
            user = userRepo.save(user);
        }catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof PropertyValueException pve) {
                String field=pve.getPropertyName();
                throw new com.hammerpulse.user_service.exception.DataIntegrityViolationException(field+" cannot be null");
            }
            if (cause instanceof ConstraintViolationException cve) {
                String constraint = cve.getConstraintName();

                if ("unique_email".equalsIgnoreCase(constraint)) {
                    throw new com.hammerpulse.user_service.exception.DataIntegrityViolationException("Email already exists");
                }
                if ("unique_username".equalsIgnoreCase(constraint)) {
                    throw new com.hammerpulse.user_service.exception.DataIntegrityViolationException("Email already exists");
                }
            }
        }

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

    public ProfileResponseDto getprofile() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        List<ProfileResponseDto> profileDetails=null;
        log.info("Getting profile for {}",authentication.getName());
        if(authentication!=null && authentication.isAuthenticated()){
             profileDetails=userRepo.getMyProfile(authentication.getName());
            if(profileDetails==null || profileDetails.isEmpty()){
                throw new ResourceNotFoundException("profile details not found");
            }
        }
        else{
            throw new UnauthorizedException("User not authenticated");
        }
        return profileDetails.get(0);
    }

    public UserDto updateUser(String username, ProfileUpdateDto profileUpdateDto) {
        User user=userRepo.findByUsername(username);
        String updatedName=profileUpdateDto.getName();
         String updatedemail =profileUpdateDto.getEmail();
         String updatedpassword =profileUpdateDto.getPassword();
         String updatedStatus =profileUpdateDto.getUser_status();
         Date updateddob =profileUpdateDto.getDob();
         String updatedphoneNumber =profileUpdateDto.getPhoneNumber();
         String updatedprofilePic =profileUpdateDto.getProfilePic();
        if(updatedName!=null && !updatedName.isEmpty()){
            user.setName(updatedName);
        }
        if(updateddob!=null){
            user.setDob(updateddob);
        }
        if(updatedemail!=null && !updatedemail.isEmpty()){
            user.setEmail(updatedemail);
        }
        if(updatedphoneNumber!=null && !updatedphoneNumber.isEmpty()){
            user.setPhoneNumber(updatedphoneNumber);
        }
        if(updatedStatus!=null){
            user.setUser_status(USER_STATUS.valueOf(updatedStatus));
        }
        if(updatedprofilePic!=null && !updatedprofilePic.isEmpty()){
            user.setProfilePic(updatedprofilePic);
        }
        if(updatedpassword!=null && !updatedpassword.isEmpty()){
            BCryptPasswordEncoder encoder =new BCryptPasswordEncoder(12);
            user.setPassword(encoder.encode(updatedpassword));
        }
        userRepo.save(user);
        return userMapper.toDto(user);
    }

    public UserDto addRoleToUser(UpdateRoleRequestDto updateRoleRequestDto){
        Role role= roleRepo.findById(updateRoleRequestDto.getRoleId());
        User user=userRepo.findByUsername(updateRoleRequestDto.getUsername());
        user.getRoles().add(role);
        userRepo.save(user);
        return userMapper.toDto(user);
    }

    public AllRolesResponse getRolesOfUser(int id) {
        User user=userRepo.findById(id);
        List<RoleDto> roles=user.getRoles().stream()
                .map(role->roleMapper.toDto(role)).toList();
        return new AllRolesResponse(roles,user.getRoles().size());
    }
}
