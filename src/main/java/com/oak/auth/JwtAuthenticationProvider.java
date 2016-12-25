package com.oak.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.oak.entities.Groups;
import com.oak.entities.Jwt;
import com.oak.entities.User;
import com.oak.repositories.JwtRepo;
import com.oak.service.GroupsService;
import com.oak.service.UsersService;



@Component("jwtAuthenticationProvider")
public class JwtAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	JwtRepo jwtRepo;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	GroupsService groupsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String id = authentication.getName();
		String token = authentication.getCredentials().toString();

		//System.out.println("Authenticating " + id + ":" + token);

		try {

			User user = usersService.getUserById(id);
			Jwt jwt = jwtRepo.findByToken(token);
			
			if (user.isActivated() && jwt!=null && jwt.getEmail().equals(id)) {
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				//grantedAuths.add(new SimpleGrantedAuthority("ROLE_anonymous"));
				
				if (user.getGroups() != null && !user.getGroups().isEmpty()) {
					String[] groups = user.getGroups().split(",");
					
					for (String group : groups) {
						Groups grp = groupsService.getGroupById(group.trim());
						if (grp != null && grp.getRoles() != null && !grp.getRoles().isEmpty()) {
							String[] roles = grp.getRoles().split(",");
							for (String role : roles) {
								grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
							}
						}

					}
				}

				Authentication auth = new UsernamePasswordAuthenticationToken(id, token, grantedAuths);

				return auth;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(JwtAuthenticationToken.class);
	}

	

}
