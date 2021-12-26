package com.ra.resume_alternative.resume;

import java.util.List;

import com.ra.resume_alternative.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByUser(User user);

}
