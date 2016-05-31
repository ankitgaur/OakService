package com.oak.auth.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.oak.entities.Users;
import com.oak.service.UsersService;

@Component("dbAuthProvider")
public class DBAuthProvider implements AuthenticationProvider {
	
	@Autowired 
	UsersService usersService;
	

	@Override
	public Authentication authenticate(Authentication authentication) {
		String id = authentication.getName();
		String password = authentication.getCredentials().toString();

		//System.out.println(id);
		//System.out.println(password);
		try {
			
			Users user = usersService.getUserById(id);
			if (user.getPassword().equals(password)) {
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + "USER"));
				Authentication auth = new UsernamePasswordAuthenticationToken(id, password, grantedAuths);
				
				return auth;
			}

		} catch (Exception e) {
			e.printStackTrace();			
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
