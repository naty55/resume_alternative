package com.ra.resume_alternative.api.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.repository.ResumeRepository;
import com.ra.resume_alternative.resume.service.ResumeService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserService;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping("/api/v1/resume")
public class resumeController {
    @Autowired 
    ResumeService resumeService;

    @Autowired
    ResumeRepository repo;

    @Autowired
    UserService userService;

    @GetMapping("get")
    public ResponseEntity<Resume> getResume(Authentication auth, @RequestParam("resumeId") Long resumeId) {
        User user = userService.getUserFromAuthentication(auth);
        Resume retrieved = resumeService.getResumeByIdAndUserId(user.getUserId(), resumeId);
        return ResponseEntity.ok().body(retrieved);
    }

    @PostMapping("create")
    public ResponseEntity<Resume> createResume(Authentication auth, @RequestParam("title") Optional<String> title, @RequestParam("style") Optional<String> style) {
        User user = userService.getUserFromAuthentication(auth);
        Resume created = resumeService.addResume(user, title, style);
        return ResponseEntity.ok().body(created);
    }

    @PostMapping("delete")
    public ResponseEntity<Resume> deleteResume(Authentication auth, @RequestParam("resumeId") Long resumeId) {
        User user = userService.getUserFromAuthentication(auth);
        resumeService.deleteResume(user.getUserId(), resumeId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
    }

    @PostMapping(path = "/update")
    public ResponseEntity<Resume> updateResume(@RequestBody Resume resume, Authentication auth) {
        if (resume.getResumeId() == null) {
            throw new HttpMessageNotReadableException("missing parameter resumeId", new BadHttpRequest(), null);
        }
        User user = userService.getUserFromAuthentication(auth);
        resumeService.update(user, resume);
        return ResponseEntity.ok().body(resume);
    }
}
