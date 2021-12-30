package com.ra.resume_alternative;

import java.util.Set;

import com.ra.resume_alternative.resume.ResumeRepository;
import com.ra.resume_alternative.resume.entity.Resume;
import com.ra.resume_alternative.resume.entity.ResumeBlock;
import com.ra.resume_alternative.resume.entity.ResumeSkill;
import com.ra.resume_alternative.resume.entity.SkillType;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ResumeAlternativeApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ResumeAlternativeApplication.class, args);
	}

	@Bean
	InitializingBean fillDataBase(UserRepository repo, ResumeRepository resumeRepo) {
		return () -> {
			// resumeRepo.deleteAll();
			repo.deleteAll();
			User user = new User("Naty", "naty@gmail.com", passwordEncoder.encode("12345678"), "11/11/1998", "ROLE_USER");
			System.out.println(user);
			repo.save(user);
			System.out.println(user);
			resumeRepo.save(new Resume(user, "untitled", Set.of(new ResumeBlock()), Set.of(new ResumeSkill("English", 4, SkillType.Language))));
			resumeRepo.save(new Resume(user, "untitled", Set.of(new ResumeBlock(), new ResumeBlock()), Set.of(new ResumeSkill("English", 4, SkillType.Language))));
			repo.save(user);
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

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
