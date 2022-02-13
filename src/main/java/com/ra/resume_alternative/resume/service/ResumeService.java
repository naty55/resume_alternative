package com.ra.resume_alternative.resume.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.repository.ResumeRepository;
import com.ra.resume_alternative.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    public static final String DEFAULT_RESUME_TITLE = "Untitled";
    public static final String DEFAULT_RESUME_STYLE = "no-style";

    @Autowired
    ResumeRepository resumeRepository;

    public List<Map<String,String>> getResumesNamesByUser(User user, Long page) {
        List<Map<String, String>> rv = new ArrayList<>();
        for (Resume r: resumeRepository.findByUser(user)) {
            Map<String, String> map = new HashMap<>();
            map.put("title", r.getTitle());
            map.put("id", String.valueOf(r.getResumeId()));
            rv.add(map);
        }
        return rv;
    }

    public Resume getResumeByIdAndUserId(Long userId, Long resumeId) throws RequestedEntityNotFoundException {
        Optional<Resume> retrievedResume = resumeRepository.findByResumeIdAndUserId(resumeId, userId);
        if(retrievedResume.isEmpty()) {
            throw new RequestedEntityNotFoundException();
        }
        return retrievedResume.get();
    }

    public Resume addResume(User user, Optional<String> title, Optional<String> stylename) {
        Resume resume = new Resume();
        resume.setStyleName(stylename.orElse(DEFAULT_RESUME_STYLE));
        resume.setTitle(title.orElse(DEFAULT_RESUME_TITLE));
        resume.setUser(user);
        return resumeRepository.save(resume);
    }

    public boolean deleteResume(Long userId, Long resumeId) {
        resumeRepository.deleteResumeByResumeIdAndUserId(resumeId, userId);
        return true;
    }

    public Resume updateResume(Long userId, Long resumeId, Optional<String> title, Optional<String> stylename) {
        Optional<Resume> retrievedResume = resumeRepository.findByResumeIdAndUserId(resumeId, userId);
        if(retrievedResume.isEmpty()) {
            throw new RequestedEntityNotFoundException();
        }
        Resume resume = retrievedResume.get();
        title.ifPresent(t -> resume.setTitle(t));
        stylename.ifPresent(s -> resume.setStyleName(s));
        return resumeRepository.save(resume);
    }

    public boolean addSkillToResume(Long userId, Long resumeId, Long skillId) {
        resumeRepository.addSkillToResume(resumeId, skillId);
        return true;
    }

    public boolean removeSkillFromResume(Long userId, Long resumeId, Long skillId) {
        resumeRepository.deleteSkillFromResume(resumeId, skillId);
        return true;
    }
    
}
