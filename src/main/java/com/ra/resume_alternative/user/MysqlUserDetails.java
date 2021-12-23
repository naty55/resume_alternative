package com.ra.resume_alternative.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
        userName = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();
        verfiedEmail = user.isVerfiedEmail();

        authorities = Arrays.stream(user.getRoles().split(","))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
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
