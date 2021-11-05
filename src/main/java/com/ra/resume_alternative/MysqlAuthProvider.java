package com.ra.resume_alternative;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MysqlAuthProvider implements AuthenticationProvider {

    @Autowired
    MysqlUserDetailsService userDetailsService;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());
        if (passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword()) && 
        userDetails.getUsername().equals(authentication.getName())) {
            Authentication a =  new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials().toString(), userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(a);
            return a;
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
