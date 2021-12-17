package com.ra.resume_alternative;

import java.util.Arrays;

import com.ra.resume_alternative.user.UserRepository;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ResumeAlternativeApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ResumeAlternativeApplication.class).
		run(args);
	}

	@Bean
	InitializingBean fillDataBase(UserRepository repo) {
		return () -> {

			// String pass = passwordEncoder.encode("12345678");
			// System.out.println(pass);

			// repo.save(new User(1, "Moshe", "Moshe@gmail.com", pass, "01/01/2020", "ROLE_USER", true));
			// repo.save(new User(2, "Naty", "Naty@gmail.com", pass, "01/02/2020", "ROLE_USER", true));
			// repo.save(new User(3, "Noa", "Noa@gmail.com", pass, "01/03/2020", "ROLE_ADMIN", true));
			// repo.save(new User(4, "Shaked", "Shaked@gmail.com", pass, "01/04/2020", "ROLE_USER", false));
			// repo.save(new User(5, "Naftaly", "Naftul@gmail.com", pass, "01/04/2020", "ROLE_USER", false));
			
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
