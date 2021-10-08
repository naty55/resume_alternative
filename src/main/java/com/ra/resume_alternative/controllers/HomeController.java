package com.ra.resume_alternative.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        Map map = new HashMap();
        map.put("name", name);
        model.addAttribute("map", map);
        return "index";
    }

    @GetMapping(value = {"/build/{user}", "/build"})
    @ResponseBody
    public String build(@PathVariable Optional<String> user) {
        if (user.isPresent()) {
            return "Hello " + user.get();
        }
        return "Hello World";
    }
    
}
