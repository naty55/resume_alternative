package com.ra.resume_alternative.controllers;

import java.util.Calendar;

import com.ra.resume_alternative.security.SecurityService;
import com.ra.resume_alternative.domain.User;
import com.ra.resume_alternative.repositories.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("auth/")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    Users usersRepo;

    @Autowired
    SecurityService securityService;
    
    @GetMapping("/login")
    String login(Model model, Authentication auth) {
        return securityService.isAuthenticatedAndView(auth, "/", "login");
    }
    // @RequestMapping("/logout")
    // RedirectView logout(Authentication auth) {
    //     return new RedirectView("/", false, true, false);
    // }

    @GetMapping("/signup")
    String signup(Model model, Authentication auth) {
        model.addAttribute("user", new User());
        return securityService.isAuthenticatedAndView(auth, "/", "signup");
    }

    @PostMapping("/signup")
    String signup(Model model, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(Calendar.getInstance().getTime());
        user.setRoles("ROLE_USER");
        usersRepo.save(user);
        Authentication auth = securityService.autoLogin(user);
        securityService.isAuthenticatedAndModel(auth, model);
        return "redirect:/";
    }

}
