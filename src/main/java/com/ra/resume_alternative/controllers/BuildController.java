package com.ra.resume_alternative.controllers;

import java.util.Optional;

import com.ra.resume_alternative.domain.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build")
public class BuildController {
    
    @GetMapping(value = {"/", ""})
    @ResponseBody
    public String index(User user) {
        System.out.println(user);
        return "build";
    }

    @GetMapping(value = {"/{user}"})
    @ResponseBody
    public String build(@PathVariable Optional<String> user) {
        if (user.isPresent()) {
            return "Hello " + user.get();
        }
        return "Hello World";
    }

}
