package com.ra.resume_alternative.repositories;
import com.ra.resume_alternative.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long>{}
