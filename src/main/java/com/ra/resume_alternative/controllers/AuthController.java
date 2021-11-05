package com.ra.resume_alternative.controllers;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import com.ra.resume_alternative.domain.User;
import com.ra.resume_alternative.repositories.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("auth/")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManagerBuilder auth;

    @Autowired
    Users usersRepo;


    
    @GetMapping("/login")
    String login(Model model, UsernamePasswordAuthenticationToken principal) {
        if (principal != null && principal.isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }
    @RequestMapping("/logout")
    RedirectView logout() {
        return new RedirectView("/", false, true, false);
    }

    @GetMapping("/signup")
    String signup(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    RedirectView signup(Model model, User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreated(Calendar.getInstance().getTime());
        user.setRoles("ROLE_USER");
        usersRepo.save(user);

        Authentication auth = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority(user.getRoles())));
        SecurityContextHolder.getContext().setAuthentication(auth);
        model.addAttribute("name", auth.getName());
        model.addAttribute("isLoogedIn", auth.isAuthenticated());
        return new RedirectView("/", false, true, false);
    }

}
