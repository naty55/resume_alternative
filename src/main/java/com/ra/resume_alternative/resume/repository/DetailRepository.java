package com.ra.resume_alternative.resume.repository;

import java.util.Optional;


import com.ra.resume_alternative.resume.entity.ResumeDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface DetailRepository extends JpaRepository<ResumeDetail, Long> {

    
    @Modifying
    @Query(value = "delete from resumes_detailss where detail_id=:detailId", nativeQuery = true)
    void deleteAllDetailResumeConnections(Long detailId);
    
    @Query("select d from details d where d.detailId=?1 and d.userId=?2")
    Optional<ResumeDetail> findByDetailIdAndUserId(Long detailId, Long userId);

    
    @Modifying
    @Query("delete from details d where d.detailId=?1 and d.userId=?2")
    void deleteBySkillIdAndUserId(Long detailId, Long userId);

    
    @Query(value = "select exists(select 1 from details where detail_id=:detailId and user_id=:userId limit 1)", nativeQuery = true)
    int isDetailExistsByDetailIdAndUserId(Long userId, Long detailId);


}
