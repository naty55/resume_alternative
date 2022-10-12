package com.ra.resume_alternative;

import java.text.ParseException;

import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {
    
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    UserRepository usersRepo;

    @Test
    void testCreateUser() throws ParseException {
        User user = new User("test_user", "test_email", "12345678", "01/01/2020", "ROLE_USER", true);
        User savedUser = usersRepo.save(user);
        User existUser = entityManager.find(user.getClass(), user.getUserId());
        assertThat(savedUser.getEmail()).isEqualTo(existUser.getEmail());
        assertThat(savedUser.getUsername()).isEqualTo(existUser.getUsername());
        assertThat(savedUser.getUserId()).isEqualTo(existUser.getUserId());
        assertThat(savedUser.getPassword()).isEqualTo(existUser.getPassword());
        assertThat(savedUser.isVerfiedEmail()).isEqualTo(existUser.isVerfiedEmail());
    }
    
    @Test
    void testDeleteUserById() throws ParseException {
        User user = new User("test_user", "test_email", "12345678", "01/01/2020", "ROLE_USER", true);
        User savedUser = usersRepo.save(user);
        usersRepo.deleteById(savedUser.getUserId());
        assertNull(usersRepo.findByEmail(savedUser.getEmail()).orElse(null));
        assertNull(usersRepo.findById(savedUser.getUserId()).orElse(null));
    }

    
    @Test
    void testDeleteUser() throws ParseException {
        User user = new User("test_user", "test_email", "12345678", "01/01/2020", "ROLE_USER", true);
        User savedUser = usersRepo.save(user);
        usersRepo.delete(savedUser);
        assertNull(usersRepo.findByEmail(savedUser.getEmail()).orElse(null));
        assertNull(usersRepo.findById(savedUser.getUserId()).orElse(null));
    }
}
