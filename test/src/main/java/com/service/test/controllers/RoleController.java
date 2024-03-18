package com.service.test.controllers;

import com.service.test.dto.ResponseDto;
import com.service.test.dto.RoleRequestDto;
import com.service.test.models.Role;
import com.service.test.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseDto createRole(@RequestBody RoleRequestDto roleRequestDto){

        return roleService.createRole(roleRequestDto);
    }

    @PostMapping("/delete")
    public ResponseDto deleteRole(@RequestBody RoleRequestDto roleRequestDto){
        return roleService.deleteRole(roleRequestDto);
    }

    @GetMapping("")
    public List<String> findAll(){
        return roleService.findAll();
    }
}
