package com.ra.resume_alternative;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
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
	MysqlAuthProvider authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
    }

    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/*", "/auth/**").permitAll()
			.antMatchers("/user/**", "/build/**").hasAnyRole("USER", "ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/auth/login")
			.and()
			.logout()
			.logoutUrl("/auth/logout")
			.logoutSuccessUrl("/")
			.deleteCookies("JSESSIONID");
	}
	
} 