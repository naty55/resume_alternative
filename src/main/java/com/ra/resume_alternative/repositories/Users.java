package com.ra.resume_alternative.repositories;
import java.util.Optional;

import com.ra.resume_alternative.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<User, Long>{
    Optional<User> findByName(String username);
}
