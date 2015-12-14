package com.tsystems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.tsystems.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().
//		antMatchers("/", "/home").permitAll().antMatchers("/admin/**").access("hasRole('ADMIN')").
//				antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')").and()
//				.formLogin().loginPage("/login").usernameParameter("ssoId").passwordParameter("password").and()
//				.exceptionHandling().accessDeniedPage("/Access_Denied");
		antMatchers("/client/**").access("hasRole('CLIENT')").
		antMatchers("/manager/**").access("hasRole('SALES_MANAGER')").and().formLogin().loginPage("/login").and().exceptionHandling().accessDeniedPage("/403")
		;
	}
	
}
