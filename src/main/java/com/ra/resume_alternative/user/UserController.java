package com.ra.resume_alternative.user;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ra.resume_alternative.resume.ResumeService;
import com.ra.resume_alternative.security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    SecurityService securityService;

    @Autowired
    UserService userService;

    @Autowired
    ResumeService resumeService;

    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        securityService.isAuthenticatedAndModel(auth, model);
        userService.putUserAsModel(model, auth);
        return "profile";
    }

    @GetMapping(value="/_getResumesNames")
    @ResponseBody
    public List<Map<String, String>> _getResumesNames(@RequestParam("page") Optional<Long> page, Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        return resumeService.getResumesNamesByUser(user, page.orElse(0L));
    }
    
    
}
