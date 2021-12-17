package com.ra.resume_alternative;

import com.ra.resume_alternative.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    SecurityService securityService;

    @RequestMapping("/")
    public String index(Model model, Authentication auth) {
        securityService.isAuthenticatedAndModel(auth, model);
        return "index";
    }

}
