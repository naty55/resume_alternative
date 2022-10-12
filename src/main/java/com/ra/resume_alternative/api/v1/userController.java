package com.ra.resume_alternative.api.v1;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ra.resume_alternative.resume.entity.ResumeDetail;
import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.user.User;

@Controller
@RequestMapping("api/v1/user")
public class userController {   
    @GetMapping("getProfile")
    public ResponseEntity<User> getProfile() {
        return null;
    }
    @GetMapping("getMe")
    public Map<String, String> getMe() {
        return null;
    }
    
    @PutMapping("addDetail")
    public ResponseEntity<ResumeDetail> addDetail(ResumeDetail detail) {
        return null;
    }
    @PutMapping("addSkill")
    public ResponseEntity<ResumeSkill> addSkill(ResumeSkill skill) {
        return null;
    }
    
    @PostMapping("updateDetail")
    public ResponseEntity<ResumeDetail> updateDetail(ResumeDetail detail) {
        return null;
    }
    @PostMapping("updateSkill")
    public ResponseEntity<ResumeSkill> updateSkill(ResumeSkill skill) {
        return null;
    }
}
