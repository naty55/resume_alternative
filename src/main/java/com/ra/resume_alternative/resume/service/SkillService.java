package com.ra.resume_alternative.resume.service;

import java.util.Optional;

import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillLevel;
import com.ra.resume_alternative.resume.entity.SkillType;
import com.ra.resume_alternative.resume.error.RequestedEntityNotFoundException;
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
        newSkill = skillRepository.save(newSkill);
        return newSkill;
    }

    public ResumeSkill updateSkill(Long userId, Long skillId, Optional<String> newName, Optional<SkillLevel> newLevel) throws RequestedEntityNotFoundException{
        Optional<ResumeSkill> retrievedSkill = skillRepository.findBySkillIdAndUserId(skillId, userId);
        if(retrievedSkill.isEmpty()) {
            throw new RequestedEntityNotFoundException();
        }
        ResumeSkill skill = retrievedSkill.get();
        newName.ifPresent(name -> skill.setSkillName(name));
        newLevel.ifPresent(skillLevel -> skill.setSkillLevel(skillLevel));
        return skillRepository.save(skill);
    }

    @Transactional
    public boolean deleteSkill(Long userId, Long skillId) {
        skillRepository.deleteBySkillIdAndUserId(skillId, userId);
        return true;
    }
}
