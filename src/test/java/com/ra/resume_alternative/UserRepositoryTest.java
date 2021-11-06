package com.ra.resume_alternative;

import java.text.ParseException;

import com.ra.resume_alternative.domain.User;
import com.ra.resume_alternative.repositories.Users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
    
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    Users usersRepo;

    @Test
    void testCreateUser() throws ParseException {
        User user = new User(1, "test_user", "test_email", "12345678", "01/01/2020", "ROLE_USER", true);
        
        
        User savedUser = usersRepo.save(user);
        
        User existUser = entityManager.find(user.getClass(), user.getId());

        assertThat(savedUser.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(savedUser.getName()).isEqualTo(existUser.getName());
        assertThat(savedUser.getId()).isEqualTo(existUser.getId());
        assertThat(savedUser.getPassword()).isEqualTo(existUser.getPassword());
        assertThat(savedUser.isVerfiedEmail()).isEqualTo(existUser.isVerfiedEmail());
    }
    
}
