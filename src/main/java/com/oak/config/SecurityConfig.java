package com.oak.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.oak.auth.provider.DBAuthProvider;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DBAuthProvider dbAuthProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  /*auth.inMemoryAuthentication().withUser("mkyong").password("123456").roles("USER");
	  auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("dba").password("123456").roles("DBA");*/
		auth.authenticationProvider(dbAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

	  http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers(HttpMethod.POST, "/users").permitAll()
		.anyRequest().authenticated().and().httpBasic().and().csrf().disable().headers().frameOptions().disable();
		
	}
}