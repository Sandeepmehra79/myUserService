package com.service.test.config;

import com.service.test.models.Role;
import com.service.test.models.User;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserToUserDetails implements UserDetails {

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;


    public UserToUserDetails(User user) {
        email = user.getEmail();
        password = user.getPassword();
//        authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))  // Create authority object from the role name
//                .collect(Collectors.toList());

        authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            // Create a SimpleGrantedAuthority object for each role and add it to the list
            System.out.println(role.getName());
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
