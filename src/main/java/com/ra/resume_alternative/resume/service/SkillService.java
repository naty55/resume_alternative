package com.ra.resume_alternative.resume.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillLevel;
import com.ra.resume_alternative.resume.entity.SkillType;
import com.ra.resume_alternative.error.RequestedEntityNotFoundException;
import com.ra.resume_alternative.resume.repository.SkillRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SkillService {

    @Autowired
    SkillRepository skillRepository;

    public ResumeSkill addSkill(Long userId, String name, SkillType skillType, SkillLevel skillLevel) {
        ResumeSkill newSkill = new ResumeSkill();
        newSkill.setUserId(userId);
        newSkill.setSkillName(name);
        newSkill.setSkillLevel(skillLevel);
        newSkill.setSkillType(skillType);
        newSkill.setUserId(userId);
        return addSkill(newSkill);
    }
    @Transactional
    public ResumeSkill addSkill(ResumeSkill skill) {
        Optional<ResumeSkill> s = skillRepository.findBySkillNameAndSkillTypeAndUserId(skill.getSkillName(), skill.getSkillType(), skill.getUserId());
        if (s.isPresent()) {
            return s.get();
        }
        return skillRepository.save(skill);
    }

    public ResumeSkill updateSkill(Long userId, Long skillId, Optional<String> newName, Optional<SkillLevel> newLevel) throws RequestedEntityNotFoundException{
        Optional<ResumeSkill> retrievedSkill = skillRepository.findBySkillIdAndUserId(skillId, userId);
        if(retrievedSkill.isEmpty()) {
            throw new RequestedEntityNotFoundException();
        }
        ResumeSkill skill = retrievedSkill.get();
        newName.ifPresent(skill::setSkillName);
        newLevel.ifPresent(skill::setSkillLevel);
        return skillRepository.save(skill);
    }

    public Set<ResumeSkill> updateOrCreate(Set<ResumeSkill> skills, Long userId) {
        if(skills == null) {
            return null;
        }
        Set<ResumeSkill> persistedSkills = new HashSet<>();
        for (ResumeSkill skill: skills) {
            skill.setUserId(userId);
            ResumeSkill s = null;
            if(skill.getSkillId() != null) { // Update
                    s = updateSkill(userId, skill.getSkillId(), Optional.of(skill.getSkillName()), Optional.of(skill.getSkillLevel()));
                } else {
                    s = addSkill(skill);
                }
            persistedSkills.add(s);
        }
        return persistedSkills;
    }

    @Transactional
    public boolean deleteSkill(Long userId, Long skillId) {
        ResumeSkill skill = skillRepository.findById(skillId).orElseThrow(RequestedEntityNotFoundException::new);
        if(skill.getUserId().equals(userId)) {
            skillRepository.deleteBySkillIdAndUserId(skillId, userId);
        } else {
            throw new RequestedEntityNotFoundException();
        }
        return true;
    }
}
