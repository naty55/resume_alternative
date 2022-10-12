package com.ra.resume_alternative.resume.repository;

import java.util.Optional;


import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SkillRepository extends JpaRepository<ResumeSkill, Long> {
    Optional<ResumeSkill> findBySkillId(Long skillId);

    @Modifying
    @Query(value = "delete from resumes_skills where skill_id=:skillId", nativeQuery = true)
    void deleteAllSkillResumeConnections(Long skillId);

    Optional<ResumeSkill> findBySkillIdAndUserId(Long skillId, Long userId);

    Optional<ResumeSkill> findBySkillNameAndSkillTypeAndUserId(String skillName, SkillType skillType, Long userId);
    
    void deleteBySkillIdAndUserId(Long skillId, Long userId);

    @Query(value = "select exists(select 1 from skills where skill_id=:skillId and user_id=:userId limit 1)", nativeQuery = true)
    int isSkillExistsBySkillIdAndUserId(Long userId, Long skillId);
    
}
