package com.ra.resume_alternative.api.v1;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.entity.ResumeDetail;
import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillLevel;
import com.ra.resume_alternative.resume.service.DetailService;
import com.ra.resume_alternative.resume.service.SkillService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserService;


@Controller
@RequestMapping("api/v1/user")
public class userController {   
    @Autowired
    UserService userService;

    @Autowired
    DetailService detailService;

    @Autowired 
    SkillService skillService;

    @GetMapping("getProfile")
    public ResponseEntity<User> getProfile(Authentication auth) {
        return ResponseEntity.ok().body(userService.getUserFromAuthentication(auth));
    }
    @GetMapping("getMe")
    public ResponseEntity<Map<String, String>> getMe(Authentication auth) {
        Map<String, String> map = new HashMap<>();
        User user = userService.getUserFromAuthentication(auth);
        map.put("username", user.getUsername());
        return ResponseEntity.ok().body(map);
    }
    
    @PutMapping("addDetail")
    public ResponseEntity<ResumeDetail> addDetail(@RequestBody ResumeDetail detail, Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        detail.setUserId(user.getUserId());
        detail = detailService.addDetail(detail);
        return ResponseEntity.status(HttpStatus.CREATED).body(detail);
    }
    @PutMapping("addSkill")
    public ResponseEntity<ResumeSkill> addSkill(@RequestBody ResumeSkill skill, Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        skill.setUserId(user.getUserId());
        skill = skillService.addSkill(skill);
        return ResponseEntity.status(HttpStatus.CREATED).body(skill);
    }
    
    @PostMapping("updateDetail")
    public ResponseEntity<ResumeDetail> updateDetail(@RequestParam("detailId") Long detailId, @RequestParam("value") Optional<String> value, Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        ResumeDetail detail = detailService.updateDetail(user.getUserId(), detailId, value);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(detail);
    }
    @PostMapping("updateSkill")
    public ResponseEntity<ResumeSkill> updateSkill(
        @RequestParam("skillId") Long skillId, 
        @RequestParam("name") Optional<String> name, 
        @RequestParam("level") Optional<SkillLevel> level, 
        Authentication auth) {
        User user = userService.getUserFromAuthentication(auth);
        ResumeSkill skill = skillService.updateSkill(user.getUserId(),skillId, name, level);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(skill);
    }

    class ResumesForUser {
        User user;
        List<Resume> resumes;
    }
}
