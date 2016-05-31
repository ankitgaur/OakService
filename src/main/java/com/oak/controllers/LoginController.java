package com.oak.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oak.entities.Users;
import com.oak.service.UsersService;
import com.oak.vo.UsersVO;

@RestController
public class LoginController {
	
	@Autowired
	UsersService usersService;
	
	@CrossOrigin
	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.POST)
	public UsersVO login(HttpSession session){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String email = authentication.getName();
		Users user = usersService.getUserById(email);
		UsersVO uvo = new UsersVO();
		uvo.setName(user.getName());
		uvo.setUsername(user.getUsername());
		//UUID uuid = UUID.randomUUID();
		//session.setAttribute("sessionToken", uuid.toString());
		
		//lvo.setToken(uuid.toString());
		
		return uvo;
		
	}

}
