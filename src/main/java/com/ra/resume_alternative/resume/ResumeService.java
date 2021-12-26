package com.ra.resume_alternative.resume;

import java.util.List;

import com.ra.resume_alternative.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    @Autowired
    ResumeRepository resumeRepository;

    public List<Resume> getResumesByUser(User user, Long page) {
        System.out.println(resumeRepository.findByUser(user));
        return resumeRepository.findByUser(user);
    }
}
