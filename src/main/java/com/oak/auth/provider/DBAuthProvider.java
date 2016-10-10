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

import com.oak.entities.Groups;
import com.oak.entities.User;
import com.oak.service.GroupsService;
import com.oak.service.UsersService;

@Component("dbAuthProvider")
public class DBAuthProvider implements AuthenticationProvider {

	@Autowired
	UsersService usersService;

	@Autowired
	GroupsService groupService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String id = authentication.getName();
		String password = authentication.getCredentials().toString();

		//System.out.println("Authenticating " + id + ":" + password);

		try {

			User user = usersService.getUserById(id);
			
			if (user.isActivated() && user.getPassword().equals(password)) {
				List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
				//grantedAuths.add(new SimpleGrantedAuthority("ROLE_anonymous"));
				
				if (user.getGroups() != null && !user.getGroups().isEmpty()) {
					String[] groups = user.getGroups().split(",");
					
					for (String group : groups) {
						Groups grp = groupService.getGroupById(group.trim());
						if (grp != null && grp.getRoles() != null && !grp.getRoles().isEmpty()) {
							String[] roles = grp.getRoles().split(",");
							for (String role : roles) {
								grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + role.trim()));
							}
						}

					}
				}

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
