package com.service.test.services;

import com.service.test.dto.ResponseDto;
import com.service.test.dto.RoleRequestDto;
import com.service.test.dto.RoleResponseDto;
import com.service.test.models.Role;
import com.service.test.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository rolesRepository;

    public ResponseDto createRole(RoleRequestDto roleRequestDto){
        ResponseDto responseDto = new ResponseDto();
        if(rolesRepository.findByName(roleRequestDto.getName()).isPresent()){
            responseDto.setMessage("Role "+ roleRequestDto.getName()+" already exist.");
        } else {
            Role role = new Role();
            role.setName(roleRequestDto.getName());
            rolesRepository.save(role);
            responseDto.setMessage("Role "+roleRequestDto.getName() +" added successfully.");
        }
            return responseDto ;
    }

    public ResponseDto deleteRole(RoleRequestDto roleRequestDto){
        ResponseDto responseDto = new ResponseDto();
        if(rolesRepository.findByName(roleRequestDto.getName()).isPresent()){
            rolesRepository.deleteByName(roleRequestDto.getName());
            responseDto.setMessage("Role "+roleRequestDto.getName()+" is deleted successfully.");
        } else {
            responseDto.setMessage("Role "+roleRequestDto.getName()+" don't exist.");
        }
            return responseDto;
    }

    public List<String> findAll(){
        List<Role> roles = rolesRepository.findAll();
        return roles.stream().map(Role::getName).toList();
    }
}
