package com.ra.resume_alternative.resume.service;


import java.text.ParseException;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTypeExcludeFilter;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillLevel;
import com.ra.resume_alternative.resume.entity.SkillType;
import com.ra.resume_alternative.resume.repository.SkillRepository;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;
import com.ra.resume_alternative.user.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest
@OverrideAutoConfiguration(enabled = false)
@TypeExcludeFilters(DataJpaTypeExcludeFilter.class)
@AutoConfigureCache
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@Transactional
@ImportAutoConfiguration
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SkillServiceTest {
    @Autowired
    SkillService skillService;
    @Autowired
    UserService userService;
    
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    EntityManagerFactory factory;

    @Autowired
    SkillRepository skillRepository;
    @Autowired
    UserRepository userRepo;
    User testUser;

    @BeforeAll
    void setUp() throws ParseException {
        userRepo.deleteAll();
        testUser = userService.createUser(new User("user", "user@gmail.com", "12345678", "12/12/2012", "ROLE_USER"));
    }

    @AfterAll
    void destroy() {
        
        userRepo.deleteById(testUser.getUserId());
    }

    @Test
    void testAddSkill() {
        ResumeSkill skill = skillService.addSkill(testUser.getUserId(), "English", SkillType.Language, SkillLevel.B);
        ResumeSkill retrivedSkill = entityManager.find(ResumeSkill.class, skill.getSkillId());
        assertThat(retrivedSkill).isNotNull();
        assertEquals(retrivedSkill.getUserId(), testUser.getUserId());
        assertEquals(retrivedSkill.getSkillLevel(),skill.getSkillLevel());
    }

    @Test
    void testDeleteSkill() {
        System.out.println("Starting delete test");
        ResumeSkill skill = skillService.addSkill(testUser.getUserId(), "Skill", SkillType.Language, SkillLevel.B);
        testUser.addSkill(skill);
        skillService.deleteSkill(testUser.getUserId(), skill.getSkillId());
        ResumeSkill retrivedSkill = skillRepository.getById(skill.getSkillId());
        // ResumeSkill retrivedSkill = entityManager.find(ResumeSkill.class, skill.getSkillId());
        System.out.println(retrivedSkill);
        System.out.println("===finish delete test===");
        assertThat(retrivedSkill).isNull();
    }

    @Test
    void testUpdateSkill() {

    }
}
