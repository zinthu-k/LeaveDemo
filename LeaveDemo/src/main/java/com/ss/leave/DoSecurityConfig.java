package com.ss.leave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ss.leave.service.EmployeeDetailService;

@EnableWebSecurity
public class DoSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private EmployeeDetailService empService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "login", "/sign-in", "/home", "/resources/**").permitAll()
		.antMatchers("/leader/**").hasRole("Leader")
		.antMatchers("/member/**").hasAnyRole("Member", "Leader")
		.anyRequest().authenticated()
	.and()
		.formLogin()
		.loginPage("/sign-in")
		.loginProcessingUrl("/login")
		.usernameParameter("mail")
		.passwordParameter("password")
		.defaultSuccessUrl("/home", true)
	.and()
		.logout()
		.logoutUrl("/logout")
		.logoutSuccessUrl("/")
		.invalidateHttpSession(true);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authmgr) throws Exception {
		authmgr.userDetailsService(empService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
