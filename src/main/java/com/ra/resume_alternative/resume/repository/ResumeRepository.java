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

    @Modifying
    @Query(value = "delete from resumes_skills where resume_id=:resumeId", nativeQuery = true)
    void deleteAllSkillsFromResume(Long resumeId);

    @Modifying
    @Query(value = "delete from resumes_details where resume_id=:resumeId", nativeQuery = true)
    void deleteAllDetailsFromResume(Long resumeId);

    @Query(value = "select exists(select 1 from resumes where resume_id=:resumeId and user_id=:userId limit 1)", nativeQuery = true)
    int isExistByREsumeIdAndUserId(Long resumeId, Long userId);

    @Query("select r from resumes r where r.resumeId=:resumeId and r.user.userId=:userId")
    Optional<Resume> findByResumeIdAndUserId(Long resumeId, Long userId);

    @Modifying
    @Query("delete from resumes r where r.resumeId=:resumeId and r.user.userId=:userId")
    void deleteResumeByResumeIdAndUserId(Long resumeId, Long userId);

    @Modifying
    @Query(value = "insert into resumes_skills (skill_id, resume_id) values (:skillId, :resumeId)", nativeQuery = true)
    void addSkillToResume(Long resumeId, Long skillId);

    @Modifying
    @Query(value="delete from resumes_skills where skill_id=:skillId and resume_id=:resumeId", nativeQuery = true)
    void deleteSkillFromResume(Long resumeId, Long skillId);

    @Modifying
    @Query(value = "insert into resumes_details (detail_id, resume_id) values (:detailId, :resumeId)", nativeQuery = true)
    void addDetailToResume(Long resumeId, Long detailId);

    @Modifying
    @Query(value="delete from resumes_details where detail_id=:detailId and resume_id=:resumeId", nativeQuery = true)
    void deleteDetailFromResume(Long resumeId, Long detailId);

    @Modifying
    @Query("update resumes r set r.title=:title where r.resumeId=:resumeId and r.user.userId=:userId")
    void updateResumeTitleByResumeIdAndUserId(Long resumeId, Long userId, String title);

    @Modifying
    @Query("update resumes r set r.styleName=:styleName where r.resumeId=:resumeId and r.user.userId=:userId")
    void updateResumeStylenameByResumeIdAndUserId(Long resumeId, Long userId, String styleName);
    

}
