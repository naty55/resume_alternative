package com.ra.resume_alternative.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/profile")
    @ResponseBody
    public String test(Model model) {
        return "User/profile/test";
    }
    
}
