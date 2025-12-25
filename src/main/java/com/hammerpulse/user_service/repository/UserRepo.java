package com.hammerpulse.user_service.repository;

import com.hammerpulse.user_service.dto.ProfileResponseDto;
import com.hammerpulse.user_service.entity.User;
import com.hammerpulse.user_service.enums.USER_STATUS;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepo {
    public ArrayList<User> findAll();
    public User save(User user);
    public User findByUsername(String username);
    public User findById(int id);
    @Query("""
            Select new com.hammerpulse.user_service.dto.ProfileResponseDto(
                 k.name,
                 k.username,
                 k.email,
                 k.user_status,
                 k.dob,
                 k.phoneNumber,
                 k.profilePic
             )
             from User k where k.username=:username""")
    public List<ProfileResponseDto> getMyProfile(@Param("username") String username);

}

