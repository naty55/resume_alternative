package com.ra.resume_alternative.build;

import java.util.Map;
import java.util.Optional;

import com.ra.resume_alternative.resume.ResumeService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/build")
public class BuildController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserService userService;

    @GetMapping("/{resumeIdentifier}}")
    public String build(@PathVariable Optional<String> resumeIdentifier) {
        if (resumeIdentifier.isPresent()) {
            return "Hello " + resumeIdentifier.get();
        }
        return "/";
    }

    @GetMapping("/create-resume")
    public String create(Authentication auth){
        User user = userService.getUserFromAuthentication(auth);
        resumeService.create(user);
        return "/";
    }

    @PostMapping("/save")
    public Map<String, String> save(Authentication auth) {
        return null;
    }

}
