package com.ra.resume_alternative.auth;


import com.ra.resume_alternative.security.SecurityService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth/")
public class AuthController {


    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;
    
    @GetMapping("login")
    String login(Model model, Authentication auth) {
        return securityService.isAuthenticatedAndViewName(auth, "/", "login");
    }

    @GetMapping("signup")
    String signup(Model model, Authentication auth) {
        model.addAttribute("user", new User());
        return securityService.isAuthenticatedAndViewName(auth, "/", "signup");
    }

    @PostMapping("signup")
    String signup(Model model, User user) {
        user = userService.createUser(user);
        Authentication auth = securityService.autoLogin(user);
        securityService.isAuthenticatedAndModel(auth, model);
        return "redirect:/";
    }

}
