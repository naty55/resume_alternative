package com.ra.resume_alternative.security;

import java.util.Arrays;

import com.ra.resume_alternative.auth.MysqlAuthProvider;
import com.ra.resume_alternative.user.MysqlUserDetailsService;
import com.ra.resume_alternative.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


/**
 * This service is for general security stuff.
 */
@Service
public class SecurityService {

    @Autowired
    MysqlAuthProvider mysqlAuthProvider;

    @Autowired
    MysqlUserDetailsService mysqlUserDetailsService;

    /**
     * Check if authentication is a valid user
     * @param auth Authentication object
     * @return returns false if the Authentication object is of type AnonymousAuthenticationToken or is null 
     * otherwise returns the value of auth
     */
    public boolean isAuthenticated(Authentication auth) {
        if (auth == null || AnonymousAuthenticationToken.class.isAssignableFrom(auth.getClass())) {
            return false;
        }
        return auth.isAuthenticated();
    }

    /**
     * Check if authentication is a valid user, and if so add "isLoggedIn", "username" to model. 
     * This method is generally used all over the place where we want to return valid model.
     * @param auth Authentication object
     * @param model Model object
     * @return returns true if auth is valid; false otherwise
     */
    public boolean isAuthenticatedAndModel(Authentication auth, Model model) {
        boolean isAuthenticated = this.isAuthenticated(auth);
        
        if(isAuthenticated) {
            model.addAttribute("isLoggedIn", true);
            model.addAttribute("username", auth.getName());
        }
        return isAuthenticated;
    }

    public String isAuthenticatedAndViewName(Authentication auth, String redirectTo, String DefaultView) {
        if (isAuthenticated(auth)) {
            return "redirect:" + redirectTo;
        }
        return DefaultView;   
    }

    public Authentication autoLogin(User user) {
        UserDetails userDetails = mysqlUserDetailsService.getByUser(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRoles())));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }
}
