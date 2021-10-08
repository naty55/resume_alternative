package com.ra.resume_alternative;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ResumeAlternativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeAlternativeApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ApplicationContext ctx) {
		return a -> {
			System.out.println(ctx.getBean("users"));
			String[] beans = ctx.getBeanDefinitionNames();
			Arrays.sort(beans);
			Stream.of(beans).
			forEach(System.out::println);
		};
	}
}
