package com.service.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto extends ResponseDto{
    private String email;
    private String password;
    private List<String> roles;
}

