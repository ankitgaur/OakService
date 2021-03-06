package com.oak.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.oak.entities.User;
import com.oak.service.UsersService;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UsersService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		
		String uri = request.getRequestURI();
		String method = request.getMethod();
		
		if(!uri.equals("/login") && !method.equals("POST") && !method.equals("PUT") && !method.equals("DELETE")){
			return true;
		}
		
		String authHeader = request.getHeader("Authorization");	
		
		if(authHeader==null || authHeader.isEmpty()){
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		
		if(uri.equals("/login")){
			String credentials = authHeader.replace("Basic ", "");
			String []upass = new String(Base64Utils.decodeFromString(credentials)).split(":"); 
			
			String email = upass[0];
			String password = upass[1];
			
			User user = userService.getUserById(email);		
			
			if(password.equals(user.getPassword())){	
				if(user.isActivated()){
					request.setAttribute("user", email);
					return true;
				}
				else{
					
					response.sendError(401, "User exists but is not activated. Please check your mailbox for an activation mail.");
					return false;
				}
			}			
		}else{
			//Users user = userService.getUserById(email);
			
			HttpSession session = request.getSession();
			System.out.println("token="+session.getAttribute("token"));
			
		}		
		
		response.sendError(401, "Email or password is invalid.");
		return false;
		
	}

}
