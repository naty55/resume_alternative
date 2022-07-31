package com.ra.resume_alternative.build;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.service.ResumeService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/build")
public class BuildController {

    @Autowired
    ResumeService resumeService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"", "/"})
    public String buildDefault(Authentication auth) {
        return "build";
    }

    @GetMapping("/{resumeIdentifier}")
    public String build(@PathVariable Long resumeIdentifier, Model model, Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        try {
            Resume resume = resumeService.getResumeByIdAndUserId(user.getUserId(), resumeIdentifier);
            model.addAttribute("resume", resume);
        } catch(RequestedEntityNotFoundException e) {
        }
        return "build";
    }

    @GetMapping("/templates")
    @ResponseBody
    public String templates(Authentication auth) {
        return "templates";
    }


}
