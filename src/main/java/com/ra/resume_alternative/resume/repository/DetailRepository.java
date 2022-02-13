package com.ra.resume_alternative.resume.repository;

import java.util.Optional;


import com.ra.resume_alternative.resume.entity.ResumeDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DetailRepository extends JpaRepository<ResumeDetail, Long> {
    
    @Query("select d from details d where d.detailId=?1 and d.userId=?2")
    Optional<ResumeDetail> findByDetailIdAndUserId(Long detailId, Long userId);

    
    @Modifying
    @Transactional
    @Query("delete from details d where d.detailId=?1 and d.userId=?2")
    void deleteBySkillIdAndUserId(Long detailId, Long userId);


}
