package com.service.test.services;

import com.service.test.config.UserToUserDetails;
import com.service.test.models.User;
import com.service.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserToUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        Optional<User> user = repository.findByEmail(username);
//
//        return user.map(UserToUserDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User "+username+ " not found"));


        // Find the user with the given username
        Optional<User> foundUser = repository.findByEmail(username);

        // Check if the user was found
        if (foundUser.isPresent()) {
            // User found, create UserDetails object using UserToUserDetails converter
            return new UserToUserDetails(foundUser.get());
        } else {
            // User not found, throw UsernameNotFoundException
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }

}
