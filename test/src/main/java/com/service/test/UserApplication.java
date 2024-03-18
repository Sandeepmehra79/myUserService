package com.service.test;

import com.service.test.dto.UserRequestDto;
import com.service.test.models.User;
import com.service.test.repository.UserRepository;
import com.service.test.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
