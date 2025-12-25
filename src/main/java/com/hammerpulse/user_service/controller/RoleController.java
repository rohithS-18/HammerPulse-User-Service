package com.hammerpulse.user_service.controller;

import com.hammerpulse.user_service.dto.AllRolesResponse;
import com.hammerpulse.user_service.dto.RoleDto;
import com.hammerpulse.user_service.service.RolesService;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class RoleController {
    @Autowired
    private RolesService rolesService;

    @PostMapping("/role")
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleDto roleDto){
        roleDto=rolesService.createRole(roleDto);
        URI location=URI.create("/role/"+roleDto.getId());
        return ResponseEntity.created(location).body(roleDto);
    }

    @PatchMapping("/role/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable("id") int id, @RequestBody RoleDto roleDto){
        return ResponseEntity.ok(rolesService.updateRole(roleDto,id));
    }

    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable("id") int id){
        int del=rolesService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/role/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("id") int id){
        return ResponseEntity.ok(rolesService.getRoleById(id));
    }

    @GetMapping("/roles")
    public ResponseEntity<AllRolesResponse> getAllRoles(){
        return ResponseEntity.ok(rolesService.getAllRoles());
    }

}
