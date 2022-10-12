package com.ra.resume_alternative.resume.repository;

import java.util.Optional;

import com.ra.resume_alternative.resume.entity.DetailType;
import com.ra.resume_alternative.resume.entity.ResumeDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface DetailRepository extends JpaRepository<ResumeDetail, Long> {

    
    @Modifying
    @Query(value = "delete from resumes_details where detail_id=:detailId", nativeQuery = true)
    void deleteAllDetailResumeConnections(Long detailId);
    
    Optional<ResumeDetail> findByDetailIdAndUserId(Long detailId, Long userId);

    Optional<ResumeDetail> findByDetailTypeAndValueAndUserId(DetailType detailType, String value, Long userId);

    void deleteByDetailIdAndUserId(Long detailId, Long userId);

    
    @Query(value = "select exists(select 1 from details where detail_id=:detailId and user_id=:userId limit 1)", nativeQuery = true)
    int isDetailExistsByDetailIdAndUserId(Long userId, Long detailId);


}
