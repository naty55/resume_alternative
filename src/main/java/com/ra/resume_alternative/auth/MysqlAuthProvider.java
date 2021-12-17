package com.ra.resume_alternative.auth;

import com.ra.resume_alternative.user.MysqlUserDetails;
import com.ra.resume_alternative.user.MysqlUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
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
        System.out.println("in mysqlAuthProvider"); 
        System.out.println(authentication.getPrincipal());
        MysqlUserDetails userDetails = (MysqlUserDetails) userDetailsService.loadUserByEmail(authentication.getPrincipal().toString());
        if (passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(
                userDetails,
                authentication.getCredentials().toString(), 
                userDetails.getAuthorities());
        }
        
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
	
}
