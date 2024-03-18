package com.service.test.services;
// https://medium.com/@arijit83work/manytomany-relationship-in-spring-boot-with-hibernate-and-jpa-35d7b4fdf3bf
import com.service.test.dto.ResponseDto;
import com.service.test.dto.UserRequestDto;
import com.service.test.dto.UserResponseDto;
import com.service.test.models.Role;
import com.service.test.models.User;
import com.service.test.repository.RoleRepository;
import com.service.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseDto signUp(UserRequestDto userRequestDto) {

        Optional<User> checkUser = userRepository.findByEmail(userRequestDto.getEmail());
        if (checkUser.isPresent()) {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("User " + userRequestDto.getEmail() + " already exits.");
            return responseDto;
        }
        String email = userRequestDto.getEmail();
        String password = userRequestDto.getPassword();
//        Set<Role> roles = userRequestDto.getRoles().stream()
//                .map(role -> {
//                    Role newRole = new Role();
//                    newRole.setName(role);
//                    return  newRole;
//                })
//                .collect(Collectors.toCollection(HashSet::new));


        UserResponseDto userResponseDto = new UserResponseDto();
//  this is critical , checking if the roles already exit or not
        Set<Role> roles = new HashSet<>();
        for (String roleName : userRequestDto.getRoles()) {
            Optional<Role> existingRole = roleRepository.findByName(roleName);
            if (existingRole.isPresent()) {
                roles.add(existingRole.get());
            } else {
                // If the role doesn't exist, create a new one
                Role newRole = new Role();
                newRole.setName(roleName);
                roles.add(newRole);
            }
        }

                // creating a user object so that it can be saved to the user table in database
                User user = new User();
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode(password));
                user.setRoles(roles);
                userRepository.save(user);


            userResponseDto.setEmail(email);
            userResponseDto.setMessage("Hi " + userRequestDto.getEmail() + " your account is created successfully");
            return userResponseDto;
        }


    public ResponseDto login(UserRequestDto userRequestDto) {
        ResponseDto responseDto = new ResponseDto();
        Optional<User> user = userRepository.findByEmail(userRequestDto.getEmail());
        System.out.println(userRequestDto.getEmail());
        System.out.println("before checking the optional object.");
         if(user.isPresent()){
             if(user.get().getPassword().equals(userRequestDto.getPassword())) {
                 System.out.println("after checking the optional object.");
                 responseDto.setMessage(userRequestDto.getEmail()+" welcome to our website.");
                 return responseDto;
             }
             System.out.println(user.get().getPassword() );
             System.out.println(userRequestDto.getPassword());
             responseDto.setMessage(userRequestDto.getEmail()+" wrong password.");
             return responseDto;
         }
        responseDto.setMessage(userRequestDto.getEmail()+" user don't exist.");
         return responseDto;
    }

    public ResponseDto deleteAccount(UserRequestDto userRequestDto){
        ResponseDto responseDto = new ResponseDto();
        Optional<User> user  = userRepository.findByEmail(userRequestDto.getEmail());
        if(user.isPresent()){
            if(user.get().getPassword().equals(userRequestDto.getPassword())){
                userRepository.deleteByEmail(userRequestDto.getEmail());
                responseDto.setMessage(userRequestDto.getEmail() +" we have successfully deleted your account");
                return responseDto;
            }
            responseDto.setMessage("wrong password");
            return responseDto;
        }
        responseDto.setMessage("user don't exist");
        return responseDto;
    }

    public List<User> findAll(){
        List<User> users = userRepository.findAll();
        return users;
    }




}
