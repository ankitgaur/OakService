package com.oak.controllers;

import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oak.vo.LoginVO;

@RestController
public class LoginController {
	@CrossOrigin
	@RequestMapping(value = "/login", produces = "application/json", method = RequestMethod.GET)
	public LoginVO login(HttpSession session){
		LoginVO lvo = new LoginVO();
		
		UUID uuid = UUID.randomUUID();
		session.setAttribute("sessionToken", uuid.toString());
		
		lvo.setToken(uuid.toString());
		
		return lvo;
		
	}

}
