package com.ra.resume_alternative.user;

import com.ra.resume_alternative.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    SecurityService securityService;

    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        securityService.isAuthenticatedAndModel(auth, model);
        return "profile";
    }
    
}
