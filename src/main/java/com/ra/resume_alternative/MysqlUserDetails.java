package com.ra.resume_alternative;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.ra.resume_alternative.domain.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MysqlUserDetails implements UserDetails{ 


    private String userName;
    private String email;
    private String password;
    private boolean verfiedEmail;
    private List<GrantedAuthority> authorities;

    public MysqlUserDetails(User user) {
        userName = user.getName();
        email    = user.getEmail();
        password = user.getPassword();
        verfiedEmail = user.isVerfiedEmail();

        authorities = Arrays.stream(user.getRoles().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    }


    public MysqlUserDetails() {}


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
        return userName;
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
        return verfiedEmail;
    }

}
