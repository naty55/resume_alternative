package com.ra.resume_alternative.resume.repository;

import java.util.List;
import java.util.Optional;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.user.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUser(User user);
    Optional<Resume> findByResumeId(Long resumeId);

    @Query("select r from resumes r where r.resumeId=?1 and r.user.userId=?2")
    Optional<Resume> findByResumeIdAndUserId(Long resumeId, Long userId);

    @Transactional
    @Modifying
    @Query("delete from resumes r where r.resumeId=?1 and r.user.userId=?2")
    void deleteResumeByResumeIdAndUserId(Long resumeId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "insert into resumes_skills (skill_id, resume_id) values (:skillId, :resumeId)", nativeQuery = true)
    void addSkillToResume(Long resumeId, Long skillId);

    @Transactional
    @Modifying
    @Query(value="delete from resumes_skills where skill_id=:skillId and resume_id=:resumeId", nativeQuery = true)
    void deleteSkillFromResume(Long resumeId, Long skillId);

    @Transactional
    @Modifying
    @Query(value = "insert into resumes_details (detail_id, resume_id) values (:detailId, :resumeId)", nativeQuery = true)
    void addDetailToResume(Long resumeId, Long detailId);

    @Transactional
    @Modifying
    @Query(value="delete from resumes_details where detail_id=:detailId and resume_id=:resumeId", nativeQuery = true)
    void deleteDetailFromResume(Long resumeId, Long skillId);
}
