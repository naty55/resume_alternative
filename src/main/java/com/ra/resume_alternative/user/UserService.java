package com.ra.resume_alternative.user;


import java.util.Calendar;
import java.util.NoSuchElementException;

import com.ra.resume_alternative.error.RequestedEntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    User getUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email).get();
        } catch(NoSuchElementException e) {
            throw new RequestedEntityNotFoundException();
        }
    }

    public void putUserAsModel(Model model, Authentication auth) {
        User user = getUserFromAuthentication(auth);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("emailAddress", user.getEmail());
    }

    public User getUserFromAuthentication(Authentication auth) {
        User user;
        if (auth.getPrincipal().getClass().isAssignableFrom(MysqlUserDetails.class)) {
            user = ((MysqlUserDetails) auth.getPrincipal()).getUser();
        } else {
            user = getUserByEmail(auth.getPrincipal().toString());
        }
        return user;
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(Calendar.getInstance().getTime());
        user.setRoles("ROLE_USER"); 
        return userRepository.save(user);
    }
}
