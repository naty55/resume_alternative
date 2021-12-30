package com.ra.resume_alternative.build;

import java.util.Map;

import com.ra.resume_alternative.resume.ResumeService;
import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/build")
public class BuildController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/"})
    public String buildDefault(Authentication auth) {
        return "/";
    }

    @GetMapping("/{resumeIdentifier}")
    public String build(@PathVariable Long resumeIdentifier, Model model, Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        Resume resume = resumeService.getResumeById(Long.valueOf(resumeIdentifier));
        if (resumeService.isResumeMatchUser(user, resume)) {
            model.addAttribute("resume", resume);
            return "build";
        }
        return "/";
    }

    @GetMapping("/create-resume")
    public String create(Authentication auth){
        User user = userService.getUserFromAuthentication(auth);
        resumeService.create(user);
        return "/";
    }

    @PostMapping("/save-resume")
    public Map<String, String> save(Authentication auth) {
        return null;
    }

}
