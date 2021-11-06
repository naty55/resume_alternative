package com.ra.resume_alternative.controllers;

import java.util.List;

import com.ra.resume_alternative.domain.User;
import com.ra.resume_alternative.repositories.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    Users repo;

    @RequestMapping("/")
    public String index(Model model, Authentication auth) {
        if (auth != null) {
            model.addAttribute("name", auth.getName());
            model.addAttribute("isLoggedIn", true);   
        }

        model.addAttribute("users", repo.findAll());
        return "index";
    }

    @GetMapping(value="/users", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> users() {
        return repo.findAll();
    }
}
