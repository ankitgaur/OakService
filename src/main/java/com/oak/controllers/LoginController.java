package com.oak.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oak.entities.User;
import com.oak.service.UsersService;
import com.oak.vo.UsersVO;

@RestController
public class LoginController {
	
	@Autowired
	UsersService usersService;
	
	@CrossOrigin
	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public UsersVO login(@CookieValue(value = "JSESSIONID") String jsessionid,HttpSession session,HttpServletResponse response){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		User user = usersService.getUserById(email);
		
		if(user!=null && user.isActivated()){
			UsersVO uvo = new UsersVO();
			uvo.setName(user.getName());
			uvo.setUsername(user.getUsername());
			uvo.setGroups(user.getGroups());
			//UUID uuid = UUID.randomUUID();
			//session.setAttribute("sessionToken", uuid.toString());
			
			//lvo.setToken(uuid.toString());
			
			if(jsessionid==null || jsessionid.equals("null"))
			{
				Cookie cookie = new Cookie("JSESSIONID", session.getId());
				cookie.setDomain("www.ipledge2nigeria.com");
				response.addCookie(cookie);
				System.out.println("Added JSESSIONID : " + session.getId());
			}
			
			return uvo;
		}
		
		return null;
	}

	@CrossOrigin
	@RequestMapping(value = "/logoff", produces = "application/json", method = RequestMethod.GET)
	public String logoff(HttpServletRequest request, HttpServletResponse response) {

		Cookie[] cookies = request.getCookies();

		for (Cookie cookie : cookies) {

			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}

		return "{\"status\":\"loggedoff\"}";
	}
	
}
