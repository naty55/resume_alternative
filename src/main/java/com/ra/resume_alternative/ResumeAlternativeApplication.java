package com.ra.resume_alternative;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Set;

import com.ra.resume_alternative.resume.Resume;
import com.ra.resume_alternative.resume.ResumeBlock;
import com.ra.resume_alternative.resume.ResumeRepository;
import com.ra.resume_alternative.resume.ResumeSkill;
import com.ra.resume_alternative.resume.SkillType;
import com.ra.resume_alternative.user.User;
import com.ra.resume_alternative.user.UserRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ResumeAlternativeApplication {

	@Autowired
	PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		new SpringApplicationBuilder(ResumeAlternativeApplication.class).
		run(args);
	}

	@Bean
	InitializingBean fillDataBase(UserRepository repo, ResumeRepository resumeRepo) {
		return () -> {
			repo.deleteAll();
			User user = new User("Naty", "naty@gmail.com", passwordEncoder.encode("12345678"), "11/11/1998", "ROLE_USER");
			repo.save(user);
			ResumeBlock resumeBlock = new ResumeBlock();
			user.addResume(new Resume(user, "untitled", Set.of(resumeBlock), Set.of(new ResumeSkill("English", 4, SkillType.Language))));
			repo.save(user);
		};
	}

	@Bean
	InitializingBean printAllBeans(org.springframework.context.ApplicationContext ctx) {
		return () -> {
			System.out.println("Beans that had been initialized on stratup\n" +
			"===================================\n");

			Arrays.stream(ctx.getBeanDefinitionNames()).sorted().forEach(System.out::println);
		};
	}

	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public SimpleDateFormat ggg() {
		return new SimpleDateFormat("MM/dd/yyyy");
	}

}
