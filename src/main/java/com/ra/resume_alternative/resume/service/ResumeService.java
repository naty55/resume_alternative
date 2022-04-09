package com.ra.resume_alternative.resume.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.repository.DetailRepository;
import com.ra.resume_alternative.resume.repository.ResumeRepository;
import com.ra.resume_alternative.resume.repository.SkillRepository;
import com.ra.resume_alternative.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResumeService {

    public static final String DEFAULT_RESUME_TITLE = "Untitled";
    public static final String DEFAULT_RESUME_STYLE = "no-style";

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired 
    DetailRepository detailRepository;
    

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

    @Transactional
    public boolean deleteResume(Long userId, Long resumeId) {
        checkForResumeByResumeIdAndUserId(resumeId, userId);
        resumeRepository.deleteResumeByResumeIdAndUserId(resumeId, userId);
        return true;
    }

    @Transactional
    public Resume updateResume(Long userId, Long resumeId, Optional<String> title, Optional<String> stylename) {
        checkForResumeByResumeIdAndUserId(resumeId, userId);
        title.ifPresent(t -> resumeRepository.updateResumeTitleByResumeIdAndUserId(resumeId, userId, t));
        stylename.ifPresent(s -> resumeRepository.updateResumeStylenameByResumeIdAndUserId(resumeId, userId, s));
        return getResumeByIdAndUserId(userId, resumeId);
    }

    @Transactional
    public boolean addSkillToResume(Long userId, Long resumeId, Long skillId) {
        checkForResumeByResumeIdAndUserId(resumeId, userId);
        checkForSkillBySkillIdAndUserId(skillId, userId);
        resumeRepository.addSkillToResume(resumeId, skillId);
        return true;
    }

    @Transactional
    public boolean removeSkillFromResume(Long userId, Long resumeId, Long skillId) {
        checkForResumeByResumeIdAndUserId(resumeId, userId);
        checkForSkillBySkillIdAndUserId(skillId, userId);
        resumeRepository.deleteSkillFromResume(resumeId, skillId);
        return true;
    }
    @Transactional
    public boolean addSDetailToResume(Long userId, Long resumeId, Long detailId) {
        checkForResumeByResumeIdAndUserId(resumeId, userId);
        checkForDetailByDetailIdAndUserId(detailId, userId);
        resumeRepository.addDetailToResume(resumeId, detailId);
        return true;
    }
    
    @Transactional
    public boolean removeDetailFromResume(Long userId, Long resumeId, Long detailId) {
        checkForResumeByResumeIdAndUserId(resumeId, userId);
        checkForDetailByDetailIdAndUserId(detailId, userId);
        resumeRepository.deleteDetailFromResume(resumeId, detailId);
        return true;
    }

    /**
     * Check if resume exists in DB, if yes return True otherwise return False
     */
    public boolean isResumeExistsByResumeIdAndUserId(Long resumeId, Long userId) {
        return resumeRepository.isExistByREsumeIdAndUserId(resumeId, userId) == 1;
    }

    /**
     * Check if resume exists in DB otherwise throw an exception.
     * if Resume is not in DB {@link RequestedEntityNotFoundException} is thrown
     * @return void
     */
    public void checkForResumeByResumeIdAndUserId(Long resumeId, Long userId) throws RequestedEntityNotFoundException {
        if(!isResumeExistsByResumeIdAndUserId(resumeId, userId)) {
            throw new RequestedEntityNotFoundException();
        }
    }

    private void checkForSkillBySkillIdAndUserId(Long skillId, Long userId) {
        if(skillRepository.isSkillExistsBySkillIdAndUserId(userId, skillId) != 1) {
            throw new RequestedEntityNotFoundException();
        }
    }
    
    private void checkForDetailByDetailIdAndUserId(Long detailId, Long userId) {
        if(skillRepository.isSkillExistsBySkillIdAndUserId(userId, detailId) != 1) {
            throw new RequestedEntityNotFoundException();
        }
    }
}
