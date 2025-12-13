package com.hammerpulse.user_service.repository;

import com.hammerpulse.user_service.entity.User;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.ArrayList;

@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepo {
    public ArrayList<User> findAll();
    public User save(User user);
}
