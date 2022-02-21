package com.ra.resume_alternative.resume.repository;

import com.ra.resume_alternative.resume.entity.ResumeSubBlock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubBlockRepository extends JpaRepository<ResumeSubBlock, Long> {
    @Modifying
    @Query("delete from subblocks s where s.block.blockId=:blockId")
    void deleteAllByBlockId(Long blockId);
}
