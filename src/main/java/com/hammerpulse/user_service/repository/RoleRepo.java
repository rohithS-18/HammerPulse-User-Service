package com.hammerpulse.user_service.repository;

import com.hammerpulse.user_service.entity.Role;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Role.class,idClass = Integer.class)
public interface RoleRepo {
    Role save(Role role);
    Role findById(int id);
    List<Role> findAll();
    void deleteById(int id);

}
