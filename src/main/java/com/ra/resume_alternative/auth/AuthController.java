package com.ra.resume_alternative.auth;

import java.util.Calendar;

import com.ra.resume_alternative.security.SecurityService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth/")
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository usersRepo;

    @Autowired
    SecurityService securityService;
    
    @GetMapping("/login")
    String login(Model model, Authentication auth) {
        return securityService.isAuthenticatedAndViewName(auth, "/", "login");
    }

    @GetMapping("/signup")
    String signup(Model model, Authentication auth) {
        model.addAttribute("user", new User());
        return securityService.isAuthenticatedAndViewName(auth, "/", "signup");
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
