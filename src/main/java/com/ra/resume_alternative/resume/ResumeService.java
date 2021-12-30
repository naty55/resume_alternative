package com.ra.resume_alternative.resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
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

    public Resume getResumeById(Long id) {
        return resumeRepository.findById(id).get();
    }

    public boolean isResumeMatchUser(User user, Resume resume) {
        return resume.getUser().equals(user);
    }

    public Resume create(User user) {
        Resume resume = new Resume();
        resume.setUser(user);
        return resumeRepository.save(resume);
    }
}
