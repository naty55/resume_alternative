package com.ra.resume_alternative.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    User getUserByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }
}
