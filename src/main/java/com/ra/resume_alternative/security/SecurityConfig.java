package com.ra.resume_alternative.security;


import com.ra.resume_alternative.auth.MysqlAuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

	@Autowired 
	MysqlAuthProvider mysqlAuthProvider;


    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(mysqlAuthProvider);
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/*", "/js/*","/css/*", "/auth/**", "/api/**").permitAll()
			.antMatchers("/build/**", "/user/**", "/api/**").hasAnyRole("USER", "ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/auth/login")
			.usernameParameter("email")
			.and()
			.logout()
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID", "ra_id");
	}


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	

	
} 