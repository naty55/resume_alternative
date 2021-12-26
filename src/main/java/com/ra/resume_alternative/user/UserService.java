package com.ra.resume_alternative.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    public void putUserAsModel(Model model, Authentication auth) {
        User user = getUserFromAuthentication(auth);
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("emailAddress", user.getEmail());
    }

    public User getUserFromAuthentication(Authentication auth) {
        User user;
        if (auth.getPrincipal().getClass() == MysqlUserDetails.class) {
            user = ((MysqlUserDetails) auth.getPrincipal()).getUser();
        } else {
            user = getUserByEmail(auth.getPrincipal().toString());
        }
        return user;
    }
}
