package com.ra.resume_alternative;

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

}
