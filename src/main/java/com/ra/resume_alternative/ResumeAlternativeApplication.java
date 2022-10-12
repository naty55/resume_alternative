package com.ra.resume_alternative;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillLevel;
import com.ra.resume_alternative.resume.entity.SkillType;
import com.ra.resume_alternative.resume.repository.ResumeRepository;
import com.ra.resume_alternative.resume.repository.SkillRepository;
import com.ra.resume_alternative.resume.service.BlockService;
import com.ra.resume_alternative.resume.service.ResumeService;
import com.ra.resume_alternative.resume.service.SkillService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;
import com.ra.resume_alternative.user.UserService;



@SpringBootApplication
public class ResumeAlternativeApplication {


	public static void main(String[] args) {
		SpringApplication.run(ResumeAlternativeApplication.class, args);
	}

	@Bean
	InitializingBean fillDataBase(UserRepository repo,ResumeService resumeService ,ResumeRepository resumeRepo, SkillService skillService,SkillRepository skillRepository, UserService userService, BlockService blockService) {
		return () -> {
			try {
			// User user = new User();
			// user.setFirstName("Naty");
			// user.setLastName("Mina");
			// user.setEmail("naty@gmail.com");
			// user.setPassword("12345678");
			// user.setUsername("naty55");
			// user.setVerfiedEmail(true);
			User user = repo.getById(1L);
			ResumeSkill skill1 = skillService.addSkill(user.getUserId(), "English", SkillType.Language, SkillLevel.D);
			skillService.addSkill(skill1);
			ResumeSkill skill2 = skillService.addSkill(user.getUserId(), "Python", SkillType.Technology, SkillLevel.A);
			ResumeSkill skill3 = skillService.addSkill(user.getUserId(), "Java", SkillType.Technology, SkillLevel.A);
			skillService.deleteSkill(user.getUserId(), skill1.getSkillId());
			} catch(Exception e) {
				System.out.println("Fill DB didn't finish");
			}
		};
	}

}
