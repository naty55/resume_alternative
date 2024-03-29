package com.ra.resume_alternative.resume.repository;


import java.util.Optional;

import com.ra.resume_alternative.resume.entity.ResumeBlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BlockRepository extends JpaRepository<ResumeBlock, Long> {

    @Modifying
    @Query(value = "update blocks set resume_id=:resumeId where block_id=:blockId", nativeQuery = true)
    void setResumeIdToBlock(Long blockId, Long resumeId);

    @Query(value = "select * from blocks inner join resumes on resumes.resume_id=blocks.resume_id and block_id=:blockId and resumes.user_id=:userId", nativeQuery = true)
    Optional<ResumeBlock> findBlockByIdAndResumeIdAndUserId(Long blockId, Long userId);

    @Modifying
    @Query(value = "delete from blocks where resume_id=:resumeId", nativeQuery = true)
    void deleteAllBlocksByResumeId(Long resumeId);

    @Modifying
    @Query("update blocks b set b.blockName=:blockName where b.blockId=:blockId")
    void updateBlockNameByBlockId(Long blockId, String blockName);

    
    @Modifying
    @Query("update blocks b set b.blockOrder=:blockOrder where b.blockId=:blockId")
    void updateBlockOrderByBlockId(Long blockId, Integer blockOrder);

    
    @Query(value = "select exists(select 1 from blocks inner join users on blocks.block_id=:blockId and users.user_id=:userId limit 1)", nativeQuery = true)
    int isExistByREsumeIdAndUserId(Long blockId, Long userId);

}
