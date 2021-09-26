package com.ra.resume_alternative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class ResumeAlternativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResumeAlternativeApplication.class, args);
	}

	@GetMapping("/")
    public String index() {
		return "test";
    }

}
