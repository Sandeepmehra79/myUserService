package com.service.test.controllers;

import com.service.test.dto.AuthRequestDto;
import com.service.test.dto.ResponseDto;
import com.service.test.dto.UserRequestDto;
import com.service.test.dto.UserResponseDto;
import com.service.test.models.User;
import com.service.test.services.JwtService;
import com.service.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    private UserRequestDto userRequestDto;


        @PostMapping("/signup")
        //    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public ResponseDto signUp(@RequestBody UserRequestDto userRequestDto ){
        return userService.signUp(userRequestDto);
        }

        @PostMapping("/login")
        public ResponseDto login (@RequestBody UserRequestDto userRequestDto){
        return userService.login(userRequestDto);
        }

        @PostMapping("/deleteAccount")
        public ResponseDto deleteAccount(@RequestBody UserRequestDto userRequestDto){
            return userService.deleteAccount(userRequestDto);
        }



        @GetMapping("/admin")  // Map this method to the root path (/user)
        public String home() {
            return "hello admin";
            // Alternatively, create a ModelAndView object for more control over the view
        }

        @GetMapping("/current-user")
        public String getLoggedInUser(Principal principal){
            return principal.getName();
        }

//        @GetMapping("")
//        public String HomePage(){
//            return "This is home page";
//        }

        @GetMapping("/test")
        public String AnotherHomePage(){
            return "This is another home page";
        }

        @GetMapping("")
        public List<User> findAll(){
            return userService.findAll();
        }

        @PostMapping("/authenticate")
        public String authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDto){
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getEmail(), authRequestDto.getPassword()));
            if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequestDto.getEmail());
            }
            else{
                throw new RuntimeException("Invalid username and/or password");
            }
        }



}
