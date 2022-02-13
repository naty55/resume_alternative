package com.ra.resume_alternative.resume.repository;

import java.util.Optional;


import com.ra.resume_alternative.resume.entity.ResumeSkill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SkillRepository extends JpaRepository<ResumeSkill, Long> {
    Optional<ResumeSkill> findBySkillId(Long skillId);

    @Query("select s from skills s where s.skillId=:skillId and s.userId=:userId")
    Optional<ResumeSkill> findBySkillIdAndUserId(Long skillId, Long userId);
    
    @Modifying
    @Transactional
    @Query("delete from skills s where s.skillId=:skillId and s.userId=:userId")
    void deleteBySkillIdAndUserId(Long skillId, Long userId);
    
}
