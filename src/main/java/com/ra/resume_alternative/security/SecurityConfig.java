package com.ra.resume_alternative.security;


import java.util.List;

import com.ra.resume_alternative.auth.MysqlAuthProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;



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
		http
			.authorizeRequests()
			.antMatchers("/*","/css/*", "/auth/**").permitAll()
			.antMatchers("/user/**", "/build/**").hasAnyRole("USER", "ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/auth/login")
			.usernameParameter("email")
			.and()
			.logout()
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID");
	}
	

	
} 