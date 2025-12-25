package com.hammerpulse.user_service.service;

import com.hammerpulse.user_service.dto.AllRolesResponse;
import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.entity.Role;
import com.hammerpulse.user_service.exception.ResourceNotFoundException;
import com.hammerpulse.user_service.mapper.RoleMapper;
import com.hammerpulse.user_service.repository.RoleRepo;
import org.hibernate.PropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolesService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleRepo roleRepo;

    public RoleDto createRole(RoleDto roleDto){
        try {
            Role role=roleMapper.toEntity(roleDto);
            role = roleRepo.save(role);
            return roleMapper.toDto(role);
        } catch (DataIntegrityViolationException ex) {
            Throwable cause = ex.getCause();
            if (cause instanceof PropertyValueException pve) {
                String field = pve.getPropertyName();
                throw new com.hammerpulse.user_service.exception.DataIntegrityViolationException(field + " cannot be null");
            }
            if (cause instanceof ConstraintViolationException cve) {
                String constraint = cve.getConstraintName();
                if ("unique_role".equalsIgnoreCase(constraint)) {
                    throw new com.hammerpulse.user_service.exception.DataIntegrityViolationException("Role already exists");
                }
            }
            throw new DataIntegrityViolationException("Data integrity violation");
        }
    }

    public RoleDto getRoleById(int id) {
        Role role=roleRepo.findById(id);
        if(role==null){
            throw new ResourceNotFoundException("Role not found with this id "+id);
        }
        return roleMapper.toDto(role);
    }

    public RoleDto updateRole(RoleDto roleDto,int id){
        Role role =roleRepo.findById(id);
        if(role==null){
            throw new ResourceNotFoundException("No Role found with this id "+id);
        }
        roleMapper.updateRoleFromDto(roleDto,role);
        try {
            roleRepo.save(role);
        }
        catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Data integrity violation", ex);
        }
        return roleMapper.toDto(role);
    }

    public AllRolesResponse getAllRoles() {
        List<Role> roleList=roleRepo.findAll();
        if(roleList==null || roleList.isEmpty()){
            throw new ResourceNotFoundException("No roles available");
        }
        List<RoleDto> roles=roleList.stream().map(role->roleMapper.toDto(role)).toList();
        return new AllRolesResponse(roles, roles.size());
    }

    public Integer deleteRole(int id) {
        roleRepo.deleteById(id);
        return id;
    }


}
