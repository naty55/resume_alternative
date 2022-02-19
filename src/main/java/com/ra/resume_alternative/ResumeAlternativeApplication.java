package com.ra.resume_alternative;

import java.util.Optional;

import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.entity.ResumeBlock;
import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillLevel;
import com.ra.resume_alternative.resume.entity.SkillType;
import com.ra.resume_alternative.resume.repository.ResumeRepository;
import com.ra.resume_alternative.resume.service.BlockService;
import com.ra.resume_alternative.resume.service.ResumeService;
import com.ra.resume_alternative.resume.service.SkillService;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;
import com.ra.resume_alternative.user.UserService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class ResumeAlternativeApplication {


	public static void main(String[] args) {
		SpringApplication.run(ResumeAlternativeApplication.class, args);
	}

	@Bean
	InitializingBean fillDataBase(UserRepository repo,ResumeService resumeService ,ResumeRepository resumeRepo, SkillService skillService, UserService userService, BlockService blockService) {
		return () -> {
			// resumeRepo.deleteAll();
			repo.deleteAll();
			User user = new User();
			user.setFirstName("Naty");
			user.setLastName("Mina");
			user.setEmail("naty@gmail.com");
			user.setPassword("12345678");
			user.setUsername("naty55");
			user.setVerfiedEmail(true);
			user = userService.createUser(user);
			ResumeSkill skill1 = skillService.addSkill(user.getUserId(), "English", SkillType.Language, SkillLevel.D);
			ResumeSkill skill2 = skillService.addSkill(user.getUserId(), "Python", SkillType.Technology, SkillLevel.A);
			ResumeSkill skill3 = skillService.addSkill(user.getUserId(), "Java", SkillType.Technology, SkillLevel.A);
			Resume r1 = resumeService.addResume(user, Optional.ofNullable("Java software Developer"), Optional.ofNullable(null));
			Resume r2 = resumeService.addResume(user, Optional.ofNullable(null), Optional.ofNullable(null));
			r1.addSkill(skill1);
			r1.addSkill(skill2);
			r1.addSkill(skill3);
			
			r2.addSkill(skill1);
			r2.addSkill(skill2);

			ResumeBlock block1 = blockService.addBlock(user.getUserId(), r2.getResumeId(), 1, "Profile");
			ResumeBlock block2 = blockService.addBlock(user.getUserId(), r2.getResumeId(), 1, "Employment History");

			user.addResume(r1);
			user.addResume(r2);
			repo.save(user);

			

			resumeService.deleteResume(user.getUserId(), r2.getResumeId());

			System.out.println(resumeService.getResumeByIdAndUserId(user.getUserId(), r1.getResumeId()));
		};
	}

	@Bean
	InitializingBean printAllBeans(org.springframework.context.ApplicationContext ctx) {
		return () -> {
			// System.out.println("Beans that had been initialized on stratup\n" +
			// "===================================\n");
			// Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
		};
	}

	

}
